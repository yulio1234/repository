/**
 * 
 */
package cn.cnct.repository.util;


/**   
*    
* 类名称：BaseService   
* 类描述：   
* 创建人：ivan       
* @version    
*    
*/
public interface BaseService<T> {

	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public ReturnMessage<T> addObject(T obj);

	/**
	 * 通过ID删除对象
	 * @param id
	 * @return
	 */
	public ReturnMessage<T> deleteObjectById(T t, long id);

	/**
	 * 更新一个对象
	 * @param t
	 * @return
	 */
	public ReturnMessage<T> updateObject(T obj);

	/**
	 * 通过主键加载对象
	 * @param id
	 * @return
	 */
	public ReturnMessage<T> queryObject(T obj,long id);

	/**
	 * 查询对象，带分页功能
	 * @param requestParam
	 * @return
	 */
	public ReturnMessage<T> list(RequestParam requestParam, final Object... obj);

}
