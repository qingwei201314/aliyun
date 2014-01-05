package im.gsj.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.transaction.annotation.Transactional;

public abstract class CommonDao<T> {
	@Resource
	private HibernateTemplate hibernateTemplate;
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> entityClass;

	public HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		return session;
	}

	public Criteria getCriteria() {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CommonDao() {
		Class c = getClass();
		Type type = c.getGenericSuperclass();
		if (type instanceof ParameterizedType) {
			Type[] types = ((ParameterizedType) type).getActualTypeArguments();
			entityClass = (Class<T>) types[0];
		}
	}

	/**
	 * 保存记录.
	 * 
	 * @param entity
	 */
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 更新记录.
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 取出指定ID的对象.
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T get(Serializable id) {
		T t = (T) getSession().get(entityClass, id);
		return t;
	}

	/**
	 * 删除一个对象.
	 * 
	 * @param entity
	 */
	public void delete(T entity) {
		getSession().delete(entity);
	}
	
	@SuppressWarnings("unchecked")
	public void deleteById(String id) {
		T t = (T) getSession().get(entityClass, id);
		getSession().delete(t);
	}

	/**
	 * 根据某一字段的值查出符合条件的列表
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<T> queryList(String property, Object value){
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq(property, value));
		List<T> list = criteria.list();
		return list;
	}
	
	/**
	 * 根据某一字段的值找出符合条件的第一个对象
	 * @param property
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public T query(String property, Object value){
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq(property, value));
		List<T> list = criteria.list();
		T result = null;
		if(list !=null && list.size() >0)
			result = list.get(0);
		return result;
	}
	
	/**
	 * 取得表的总记录数
	 * 
	 * @return
	 */
	public Long getCount(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		Long totalCount = (Long) criteria.uniqueResult();
		return totalCount;
	}

	/**
	 * 取得指定页的记录
	 */
	@SuppressWarnings("unchecked")
	public List<T> listByPage(Criteria criteria, int pageNo, int pageSize) {
		int firstResult = (pageNo - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		List<T> list = criteria.list();
		return list;
	}

	// public Page<T> getPage(Criteria criteria, int pageNo, int pageSize) {
	// // 查出总记录数.
	// Long total = getCount(criteria);
	// Page<T> page = new Page<T>(pageNo, pageSize, total);
	// // 查找当前页记录.
	// List<T> list = listByPage(criteria, pageNo, pageSize);
	//
	// page.setList(list);
	// return page;
	// }

}
