/**
 * 
 */
package cn.cnct.repository.util;

/**   
*    
* 类名称：BaseParam   
* 类描述：   基本参数类
* 创建人：ivan       
* @version    
*    
*/
public class BaseParam {

	/**
	 * 当前页
	 */
	private int page_num = 0;

	/**
	 * 每页记录数
	 */
	private int page_size = 10;

	/**
	 * 排序方式
	 */
	private String sort_way = " asc";

	/**
	 * 排序字段
	 */
	private String sort_field = "";

	/**
	 * 当前请求地址
	 */
	private String http_request_url = "";

	public String getHttp_request_url() {
		return http_request_url;
	}

	public void setHttp_request_url(String httpRequestUrl) {
		http_request_url = httpRequestUrl;
	}

	public int getPage_num() {
		return page_num;
	}

	public void setPage_num(int pageNum) {
		page_num = pageNum;
	}

	public int getPage_size() {
		return page_size;
	}

	public void setPage_size(int pageSize) {
		page_size = pageSize;
	}

	public String getSort_way() {
		return sort_way;
	}

	public void setSort_way(String sortWay) {
		sort_way = sortWay;
	}

	public String getSort_field() {
		return sort_field;
	}

	public void setSort_field(String sortField) {
		sort_field = sortField;
	}

}
