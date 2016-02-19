/**
 * 
 */
package cn.cnct.repository.util;

import java.io.Serializable;


/**   
*    
* 类名称：RequestParam   
* 类描述：   请求参数封装
* 创建人：ivan       
* @version    
*    
*/
public class RequestParam extends BaseParam implements Serializable {

	private static final long serialVersionUID = -6455394873883618430L;

	/**
	 * 商品编码
	 */
	private String spbm = "";

	/**
	 * 商品名称
	 */
	private String spmc = "";

	/**
	 * 发票代码
	 */
	private String fpdm = "";

	/**
	 * 发票号码
	 */
	private String fphm = "";
	/**
	 * 纳税人识别号
	 */
	private String nsrsbh = "";

	/**
	 * 纳税人名称
	 */
	private String nsrmc = "";

	/**
	 * 产品代码
	 */

	private String productCode;

	/**
	 * 当前页码
	 */
	private int pageNo = 1;

	/**
	 * 状态
	 */
	private int status = 0;

	/**
	 * 需要的ID号
	 */
	private long id = 0;

	/**
	 * 自定义HQL语句
	 */
	private String custom_hql = "";

	public String getCustom_hql() {
		return custom_hql;
	}

	public void setCustom_hql(String customHql) {
		custom_hql = customHql;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNsrsbh() {
		return nsrsbh;
	}

	public String getSpbm() {
		return spbm;
	}

	public void setSpbm(String spbm) {
		this.spbm = spbm;
	}

	public String getSpmc() {
		return spmc;
	}

	public void setSpmc(String spmc) {
		this.spmc = spmc;
	}

	public String getFpdm() {
		return fpdm;
	}

	public void setFpdm(String fpdm) {
		this.fpdm = fpdm;
	}

	public String getFphm() {
		return fphm;
	}

	public void setFphm(String fphm) {
		this.fphm = fphm;
	}

	public int getPageNo() {
		return pageNo;

	}

	public void setNsrsbh(String nsrsbh) {
		this.nsrsbh = nsrsbh;
	}

	public String getNsrmc() {
		return nsrmc;
	}

	public void setNsrmc(String nsrmc) {
		this.nsrmc = nsrmc;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
