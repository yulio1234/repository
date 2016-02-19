/**
 * 
 */
package cn.cnct.repository.util;

import java.io.Serializable;
import java.util.List;

/**   
*    
* 类名称：ReturnMessage   
* 类描述：   
* 创建人：ivan       
* @version    
 * @param <T>
*    
*/
public class ReturnMessage<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 返回结果状态码
	 */
	private String code = "";

	/**
	 * 结果说明
	 */
	private String mesg = "";

	/**
	 * 指定对象数据集合
	 */
	private List<T> dataList;

	/**
	 * 分页信息
	 */
	private SplitPage splitPage;

	/**
	 * 指定的单独对象
	 */
	private T object;

	public ReturnMessage() {
		super();
	}

	/**
	 * @param code
	 * @param mesg
	 * @param returnData
	 */
	public ReturnMessage(String code, String mesg, T object) {
		super();
		this.code = code;
		this.mesg = mesg;
		this.object = object;
	}

	/**
	 * @param code
	 * @param mesg
	 * @param dataList
	 */
	public ReturnMessage(String code, String mesg, List<T> dataList) {
		super();
		this.code = code;
		this.mesg = mesg;
		this.dataList = dataList;
	}
	
	
	/**
	 * @param code
	 * @param mesg
	 * @param dataList
	 * @param splitPage
	 * @param object
	 */
	public ReturnMessage(String code, String mesg, List<T> dataList,
			SplitPage splitPage, T object) {
		super();
		this.code = code;
		this.mesg = mesg;
		this.dataList = dataList;
		this.splitPage = splitPage;
		this.object = object;
	}

	/**
	 * @param code
	 * @param mesg
	 */
	public ReturnMessage(String code, String mesg) {
		super();
		this.code = code;
		this.mesg = mesg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMesg() {
		return mesg;
	}

	public void setMesg(String mesg) {
		this.mesg = mesg;
	}

	public List<T> getDataList() {
		return dataList;
	}

	public void setDataList(List<T> dataList) {
		this.dataList = dataList;
	}

	public T getObject() {
		return object;
	}

	public void setObject(T object) {
		this.object = object;
	}

	public SplitPage getSplitPage() {
		return splitPage;
	}

	public void setSplitPage(SplitPage splitPage) {
		this.splitPage = splitPage;
	}
}
