package cn.cnct.repository.util;



/**数据分页实体*/
import java.io.Serializable;

public class SplitPage implements Serializable {

    private static final long serialVersionUID = 7337910362036192277L;

    /** 当前页号 */
    private Integer pageNum = 1;
    /** 每页显示条数 */
    private Integer pageSize = 5;
    /** 总记录数 */
    private Integer rowCount = 0;
    /** 分页变量，做多显示分页信息的条数 */
    private int liststep = 5;
    /** http请求action */
    private String httpreqStr = "";
    /** 默认后缀名 */
    private String suffix = ".html";

    /** 扩展请求参数 */
    private String prameter = "";

    public int getStartNum(){
		return (pageNum-1) * pageSize;
    }
    public String getPrameter() {
	return prameter;
    }
    
    public void setPrameter(String prameter) {
	this.prameter = prameter;
    }

    public String getSuffix() {
	return suffix;
    }

    public void setSuffix(String suffix) {
	this.suffix = suffix;
    }

    public String getHttpreqStr() {
	return httpreqStr;
    }

    public void setHttpreqStr(String httpreqStr) {
	this.httpreqStr = httpreqStr;
    }

    public int getListstep() {
	return liststep;
    }

    public void setListstep(int liststep) {
	this.liststep = liststep;
    }

    public int getPageCount() {
	int pageCount = (getRowCount() + getPageSize() - 1) / getPageSize();
	if (pageCount == 0) {
	    pageCount = 1;
	}
	return pageCount;
    }

    public int getPageNum() {
	if (pageNum == null || pageNum == 0) {
	    pageNum = 1;
	}
	return pageNum;
    }

    public void setPageNum(Integer pageNum) {
	this.pageNum = pageNum;
    }

    public int getPageSize() {
	if (pageSize == null) {
	    pageSize = 8;
	}
	return pageSize;
    }

    public void setPageSize(Integer pageSize) {
	this.pageSize = pageSize;
    }

    public int getRowCount() {
	if (rowCount == null) {
	    rowCount = 0;
	}
	return rowCount;
    }

    public void setRowCount(Integer rowCount) {
	this.rowCount = rowCount;
    }

    public int getStartRowNum() {
	return (getPageNum() - 1) * getPageSize();
    }

    /**
     * 后台分页样式
     * 
     * @方法名称：
     *@return
     * 
     */
    public String getSplitPageString() {
	int pageNo = this.getPageNum();
	String upPage = "";
	String nextPage = "";
	String str = "";
	int pagesCount = this.getPageCount();
	if (pageNo > 1) {
	    upPage = "<a onclick=\""+this.prameter+"("+(pageNo - 1)+")" + "\">上一页</a>";
	}else{
		upPage = "<a onclick=\"\" >上一页</a>";
	}
	
	if (pagesCount<=liststep) {
		for (int i = 1; i < pagesCount+1; i++) {
				if (i != pageNo) {
					str += "<a onclick=\""+this.prameter+"("+ i +")" + "\">"
					+ i + "</a>";
			    } else {
			    	str += "<a onclick=\""+this.prameter+"("+ i +")" + "\" class='selectd'>"
					+ i + "</a>";
			    }
		}
	}else{
		int total=liststep;
		int qtotal=0;
		int htotal=0;
		if ((pageNo-liststep/2)>=1&&(pageNo+liststep/2)<=pagesCount) {
			qtotal=liststep/2;
			htotal=liststep/2;
		}else if((pageNo-liststep/2)<1){
			qtotal=pageNo-1;
			htotal=total-qtotal-1;
		}else if((pageNo+liststep/2)>pagesCount){
			htotal=pagesCount-pageNo;
			qtotal=total-htotal-1;
		}
		for (int i = qtotal; i >0 ; i--) {
			str += "<a onclick=\""+this.prameter+"("+ (pageNo-i) +")" + "\">"
				+ (pageNo-i) + "</a>";
		}
		str += "<a onclick=\""+this.prameter+"("+ pageNo +")" + "\" class='selectd'>"
			+ pageNo + "</a>";
		for (int i = 1; i <=htotal; i++) {
			str += "<a onclick=\""+this.prameter+"("+ (pageNo+i) +")" + "\">"
				+ (pageNo+i) + "</a>";
		    
		}
	}
	
	if (pageNo != pagesCount) {
	    nextPage = "<a onclick=\""+this.prameter+"("+(pageNo + 1)+")" + "\">下一页</a>";
	}else{
		nextPage = "<a onclick=\"\">下一页</a>";
	}
	return  upPage + str + nextPage ;
    }

    /**
     * 前台分页样式
     * 
     * @方法名称：
     *@return
     * 
     */
    public String pagination() {
	if (this.getRowCount() <= this.getPageSize()) {
	    return "";
	} else {
	    int pageNo = this.getPageNum();
	    String prameter = "";
	    String start = "<div class=\"pagination\"> <ul>";
	    String end = "</ul></div>";
	    String upPage = "";
	    String nextPage = "";
	    String str = "";
	    int pagescount = this.getPageCount();
	    if (pagescount < pageNo) {
		pageNo = pagescount;
	    }
	    if (pageNo < 1) {
		pageNo = 1;
	    }
	    int listbegin = (pageNo - (int) Math.ceil((double) liststep / 2));
	    if (listbegin < 1) {
		listbegin = 1;
	    }
	    int listend = pageNo + liststep / 2;
	    if (listend > pagescount) {
		listend = pagescount + 1;
	    }
	    upPage = "<li  class=\"disabled borderR0 arrow\"><a href="
		    + getHttpreqStr() + (pageNo - 1) + prameter
		    + ">«</a><div id=\"fyleft\"></div></li>";
	    int y = listend - 1;
	    for (int i = listbegin; i < listend; i++) {
		String s = "borderR0";
		if (y == i) {
		    s = "";
		}
		if (i != pageNo) {
		    str += "<li class=" + s + "><a href=" + getHttpreqStr() + i
			    + prameter + ">" + i + "</a></li>";
		} else {
		    str += "<li class=\"" + s + " arrow\"><a class=\"hover\" href=\"\">" + i
			    + "</a></li>";
		}

	    }
	    nextPage = "<li class=\"borderL0 arrow\"><a href="
		    + getHttpreqStr() + (pageNo + 1) + prameter
		    + ">»</a><div id=\"fyright\"></div></li>";
	    return start + upPage + str + nextPage + end;
	}
    }

    public SplitPage() {
	super();
    }

    public SplitPage(Integer pageNum, Integer pageSize, Integer rowCount,
	    int liststep, String httpreqStr, String suffix) {
	super();
	this.pageNum = pageNum;
	this.pageSize = pageSize;
	this.rowCount = rowCount;
	this.liststep = liststep;
	this.httpreqStr = httpreqStr;
	this.suffix = suffix;
    }
}
