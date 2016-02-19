package cn.cnct.repository.util;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.engine.SessionFactoryImplementor;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * 
 * 
 * @类名称：BaseDAOImpl
 * @类描述：统一数据访问基类接口 方法实现
 * @时间：2014-5-26
 * @创建人：都市放牛
 * @version
 * 
 */
@SuppressWarnings("all")
public class BaseDAOImpl extends HibernateDaoSupport implements BaseDAO {

	public void save(Object obj) {
		getHibernateTemplate().save(obj);
	}

	public Serializable saveAndReturnObj(Object obj) {
		return getHibernateTemplate().save(obj);
	}

	public void update(Object obj) {
		getHibernateTemplate().update(obj);
	}

	public void deleteById(Class clazz, Serializable id) {
		getHibernateTemplate().delete(getHibernateTemplate().load(clazz, id));
	}

	public Object loadById(Class clazz, Serializable id) {
		return getHibernateTemplate().get(clazz, id);
	}

	public int countAll(String clazz) {
		final String hql = "select count(*) from " + clazz + " as a";
		Long count = (Long) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				query.setMaxResults(1);
				return query.uniqueResult();
			}
		});
		return count.intValue();
	}

	public List query(String hql, final Object... paramObj) {
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql1);
				setQueryParam(query, paramObj);
				return query.list();
			}
		});
	}

	public List query(String hql, int pageNo, int pageSize, final Object... paramObj) {
		final int pNo = pageNo;
		final int pSize = pageSize;
		final String hql1 = hql;
		return getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql1);
				setQueryParam(query, paramObj);
				query.setMaxResults(pSize);
				query.setFirstResult((pNo - 1) * pSize);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
	}

	public int queryCount(String hql, final Object... paramObj) {
		final String counthql = hql;
		Query query = getSession().createQuery(hql);
		setQueryParam(query, paramObj);
		int i = Integer.parseInt((query.iterate().next()).toString());
		return i;
		// Long count = (Long) getHibernateTemplate().execute(
		// new HibernateCallback() {
		// public Object doInHibernate(Session session)
		// throws HibernateException {
		// Query query = session.createQuery(counthql);
		// // setQueryParam(query, paramObj);
		// return ((Number)query.uniqueResult());
		// }
		// });
		// return count.intValue();
	}

	public void saveOrUpdate(Object obj) {
		getHibernateTemplate().saveOrUpdate(obj);
	}

	public int update(String hql) {
		final String hql1 = hql;
		return ((Integer) getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql1);
				return query.executeUpdate();
			}
		})).intValue();
	}

	public List getListByHQLAndCount(final String hql, final int count, final Object... paramObj) {
		List list = getHibernateTemplate().executeFind(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql);
				setQueryParam(query, paramObj);
				query.setMaxResults(count);
				List result = query.list();
				if (!Hibernate.isInitialized(result))
					Hibernate.initialize(result);
				return result;
			}
		});
		return list;
	}

	public Object getObjectByHQL(String hql, final Object... paramObj) {
		final String hql1 = hql;
		return getHibernateTemplate().execute(new HibernateCallback() {
			public Object doInHibernate(Session session) throws HibernateException {
				Query query = session.createQuery(hql1);
				setQueryParam(query, paramObj);
				Object object = query.setMaxResults(1).uniqueResult();
				return object;
			}
		});
	}

	public int exexuteSQL(String sql, Object... obj) {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		this.setQueryParam(query, obj);
		int r = query.executeUpdate();
		return r;
	}

	public List<Map<String, Object>> getDataListBySQL(String sql, Object... paramObj) throws Exception {
		Session session = this.getHibernateTemplate().getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery(sql);
		this.setQueryParam(query, paramObj);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> list = query.list();
		return list;
	}

	/**
	 * 设置查询条件参数
	 * @param query
	 * @param obj
	 */
	private void setQueryParam(Query query, Object... paramObj) {
		if (paramObj != null) {
			for (int i = 0; i < paramObj.length; i++) {
				query.setParameter(i, paramObj[i]);
			}
		}
	}

	public Connection getConnection() {
		ConnectionProvider cp = ((SessionFactoryImplementor) getHibernateTemplate().getSessionFactory()).getConnectionProvider();
		try {
			return cp.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}