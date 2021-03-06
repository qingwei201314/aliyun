package im.gsj.dao;

import im.gsj.util.Page;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
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
	@Transactional
	public void save(T entity) {
		getSession().save(entity);
	}

	/**
	 * 更新记录.
	 * 
	 * @param entity
	 */
	@Transactional
	public void update(T entity) {
		getSession().update(entity);
	}

	/**
	 * 保存或更新记录
	 * 
	 * @param entity
	 */
	@Transactional
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 取出指定ID的对象.
	 * 
	 * @param entityClass
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public T get(Serializable id) {
		T t = (T) getSession().get(entityClass, id);
		return t;
	}

	/**
	 * 删除一个对象.
	 * 
	 * @param entity
	 */
	@Transactional
	public void delete(T entity) {
		getSession().delete(entity);
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public void deleteById(String id) {
		T t = (T) getSession().get(entityClass, id);
		getSession().delete(t);
	}

	/**
	 * 根据某一字段的值查出符合条件的列表
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> queryList(String property, Object value) {
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq(property, value));
		List<T> list = criteria.list();
		return list;
	}

	/**
	 * 根据某一字段的值找出符合条件的第一个对象
	 * 
	 * @param property
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public T query(String property, Object value) {
		Criteria criteria = getCriteria();
		criteria.add(Restrictions.eq(property, value));
		List<T> list = criteria.list();
		T result = null;
		if (list != null && list.size() > 0)
			result = list.get(0);
		return result;
	}

	/**
	 * 取得表的总记录数
	 */
	@Transactional(readOnly = true)
	public Long getCount(Criteria criteria) {
		criteria.setFirstResult(0);
		criteria.setProjection(Projections.rowCount());
		Long totalCount = (Long) criteria.uniqueResult();
		return totalCount;
	}

	/**
	 * 取得指定页的记录
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<T> listByPage(Criteria criteria, int pageNo, int pageSize) {
		criteria.setProjection(null);
		int firstResult = (pageNo - 1) * pageSize;
		criteria.setFirstResult(firstResult);
		criteria.setMaxResults(pageSize);
		List<T> list = criteria.list();
		return list;
	}

	/**
	 * 用默认的pageSize
	 */
	public Page<T> getPage(Criteria criteria, int pageNo) {
		Page<T> page = new Page<T>();
		page.setPageNo(pageNo);
		
		// 查出总记录数.
		Long total = getCount(criteria);
		page.setTotal(total);
		
		// 查找当前页记录.
		if(total >= (pageNo-1) * page.getPageSize()){
			List<T> list = listByPage(criteria, pageNo, page.getPageSize());
			page.setList(list);
		}
		
		return page;
	}

	public Page<T> getPage(Criteria criteria, int pageNo, int pageSize) {
		// 查出总记录数.
		Long total = getCount(criteria);
		Page<T> page = new Page<T>(pageNo, pageSize, total);
		// 查找当前页记录.
		List<T> list = listByPage(criteria, pageNo, pageSize);
		page.setList(list);
		return page;
	}

	public Query listByHql(String hql, int pageNo, int pageSize) {
		Query query = getSession().createQuery(hql);
		query.setFirstResult((pageNo -1)*pageSize);
		query.setMaxResults(pageSize);
		return query;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Page pageByHql(String hql, String selectCount, int pageNo, Class<?> resultClass, Object... params) {
		//查出总记录数
		String countHql = StringUtils.substringAfter(hql, "from");
		countHql = selectCount + " from " + countHql;
		Query query = getSession().createQuery(countHql);
		query = setPrameter(query, params);
		Long total = (Long)query.uniqueResult();
		
		Page page = new Page(pageNo, total);
		if(total >= (pageNo-1) * page.getPageSize()){
			Query queryList = listByHql(hql, pageNo, page.getPageSize());
			queryList = setPrameter(queryList, params);
			queryList.setResultTransformer(Transformers.aliasToBean(resultClass));
			List list = queryList.list();
			page.setList(list);
		}
		return page;
	}
	
	private Query setPrameter(Query query, Object... params){
		if(params !=null && params.length>0){
			for(int i=0; i< params.length; i++)
				query.setParameter(i, params[i]);
		}
		return query;
	}
}
