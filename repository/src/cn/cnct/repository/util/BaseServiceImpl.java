/**
 * 
 */
package cn.cnct.repository.util;

import java.util.List;



/**   
*    
* 类名称：BaseServiceImpl   
* 类描述：   
* 创建人：ivan       
* @version    
*    
*/
public class BaseServiceImpl<T> implements BaseService<T> {

	private BaseDAO dao;

	public ReturnMessage<T> addObject(T obj) {
		ReturnMessage<T> returnMessage = null;
		try {
			dao.save(obj);
			returnMessage = new ReturnMessage<T>("100", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage = new ReturnMessage<T>("999", e.getMessage());
		}
		return returnMessage;
	}

	public ReturnMessage<T> deleteObjectById(T t,long id) {
		ReturnMessage<T> returnMessage = null;
		try {
			dao.deleteById(t.getClass(), id);
			returnMessage = new ReturnMessage<T>("100", "成功");
		} catch (Exception e) {
			e.printStackTrace();
			returnMessage = new ReturnMessage<T>("999", e.getMessage());
		}
		return returnMessage;
	}

	public ReturnMessage<T> updateObject(T obj) {
		ReturnMessage<T> returnMessage = null;
		try {
			dao.update(obj);
			returnMessage = new ReturnMessage<T>("100", "成功");
		} catch (Exception e) {
			returnMessage = new ReturnMessage<T>("999", e.getMessage());
		}
		return returnMessage;
	}
	
	@SuppressWarnings("unchecked")
	public ReturnMessage<T> queryObject(T t,long id){
		ReturnMessage<T> returnMessage = null;
		try {
			T obj=(T)dao.loadById(t.getClass(), id);
			returnMessage = new ReturnMessage<T>("100", "成功");
			returnMessage.setObject(obj);
		} catch (Exception e) {
			returnMessage = new ReturnMessage<T>("999", e.getMessage());
		}
		return returnMessage;
	}

	@SuppressWarnings("unchecked")
	public ReturnMessage<T> list(RequestParam requestParam,final Object...obj) {
		ReturnMessage<T> returnMessage = null;
		try {
			String hql =requestParam.getCustom_hql();
			String linkStr ="?page_num=";
			List<T> list = dao.query(hql.toString(), requestParam
					.getPage_num(), requestParam.getPage_size(),obj);

			// 获取总记录数
			int rowCount =dao.queryCount("select count(*)" + hql,obj);

			// 分页信息
			SplitPage splitPage = new SplitPage();
			splitPage.setRowCount(rowCount);
			splitPage.setHttpreqStr(requestParam.getHttp_request_url()
					+ linkStr);
			splitPage.setPageNum(requestParam.getPage_num());
			splitPage.setPageSize(requestParam.getPage_size());

			// 查询结果
			returnMessage = new ReturnMessage("100", "成功", null);
			returnMessage.setDataList(list);
			returnMessage.setSplitPage(splitPage);
		} catch (Exception e) {
			returnMessage = new ReturnMessage("999", e.getMessage(), null);
		}
		return returnMessage;
	}

	public BaseDAO getDao() {
		return dao;
	}

	public void setDao(BaseDAO dao) {
		this.dao = dao;
	}

}
