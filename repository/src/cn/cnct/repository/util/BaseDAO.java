package cn.cnct.repository.util;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;

/**
 * 
 * 
 * @类名称：BaseDAO
 * @类描述：统一数据访问基类接口
 * @时间：2014-5-26
 * @创建人：都市放牛
 * @version
 * 
 */
@SuppressWarnings("all")
public interface BaseDAO {

	/**
	 * 保存一个对象
	 * @param obj
	 */
	public void save(Object obj);

	/**
	 * 保存，并返回对象
	 * @param obj 需要保存的对象
	 * @return 保存后的对象
	 */
	public Serializable saveAndReturnObj(Object obj);

	/**
	 * 更新对象
	 * @param obj 需要更新的实体
	 */
	public void update(Object obj);

	/**
	 * 根据主键删除对象
	 * @param clazz 要删除的对象
	 * @param id 删除的主键ID号
	 */
	public void deleteById(Class clazz, Serializable id);

	/**
	 * 根据主键ID加载对象
	 * @param clazz 需要加载的对象
	 * @param id 对象主键ID
	 * @return 加载后的对象
	 */
	public Object loadById(Class clazz, Serializable id);
	
	/**
	 * 通过HQL语句条件查询，返回一条记录
	 * @param hql HQL语句
	 * @param paramObj 条件查询参数
	 * @return
	 */
	
	public Object getObjectByHQL(String hql, final Object... paramObj);

	/**
	 * 获取某个对象的结果集数量
	 * @param clazz
	 * @return 结果集数量
	 */
	public int countAll(String clazz);

	/**
	 * 获取所有满足条件的结果
	 * @param hql HQL语句
	 * @param paramObj 查询参数
	 * @return
	 */
	public List query(String hql, Object... paramObj);

	/**
	 * 分页查询
	 * @param hql分页查询HQL
	 * @param pageNo当前页码
	 * @param pageSize每页显示记录数
	 * @param paramObj 查询参数
	 * @return查询结果
	 */
	public List query(String hql, int pageNo, int pageSize, Object... paramObj);

	/**
	 * 通过HQL获取 记录总数
	 * @param hql HQL语句
	 * @param paramObj 查询参数
	 * @return
	 */
	public int queryCount(String hql, Object... paramObj);

	/**
	 * 保存或更新指定的持久化对象
	 * @param obj
	 */
	@Deprecated
	public void saveOrUpdate(Object obj);

	/**
	 * 通过HQL条件更新数据
	 * @param hql
	 * @return 返回更新成功的记录条数
	 */
	public int update(String hql);

	/**
	 * 通过HQL获取指定数量的结果集
	 * @param hql HQL语句
	 * @param count 返回最大数量
	 * @param paramObj 查询参数
	 * @return
	 */
	public List getListByHQLAndCount(final String hql, final int count,
			final Object... paramObj);

	/**
	 * 通过原始SQL语句查询数据
	 * @param sql
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, Object>> getDataListBySQL(String sql, Object... obj)
			throws Exception;
	
	/**
	 * 执行SQL
	 * @param sql SQL语句
	 * @param obj 参数
	 * @return
	 */
	public int exexuteSQL(String sql,Object...obj);

	/**
	 * 从连接池中取得一个JDBC连接
	 * @return
	 */
	public Connection getConnection();

	/** 装载指定类的所有持久化对象 */
	// public List listAll(String clazz);

	/** 分页装载指定类的所有持久化对象 */
	// public List listAll(String clazz, int pageNo, int pageSize);

	/** 通过HQL 分页装载指定类的所有持久化对象 */
	// public List listAllByHQL(String hql, int pageNo, int pageSize);
	
}
