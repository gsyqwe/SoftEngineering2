package persistence.hibernate.implement;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import persistence.hibernate.helper.HibernateUtil;
import persistence.hibernate.service.IODataBaseExtendion;

/**
 * 使用Hibernate操作數據庫, 實現IODataBaseExtendtion接口
 * 
 * @version 2017/12/25
 * @author Lai Kin Meng
 */
public class IOStrategyUseHibernate<T> implements IODataBaseExtendion<T> {
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	/*
	 * 查詢語句
	 */
	private String command;
	/*
	 * 查詢語句的參數
	 */
	private String parameter;

	@Override
	public Boolean inOne(T object) {
		Session session = sessionFactory.openSession();
		try {
			session.beginTransaction();
			session.save(object);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) { // id重覆
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		}
		return true;
	}

	@Override
	public Boolean inAll(Iterator<T> objects) {
		Boolean result = false;
		while (objects.hasNext()) {
			result &= this.inOne(objects.next());
		}
		return result;
	}

	@Override
	public Iterator<T> outAll(Class<T> clazz) {
		// creating seession factory object
		Session session = sessionFactory.getCurrentSession();
		try {
			session.getTransaction().begin();
			String hql = "from " + clazz.getName();
			// Query query = session.createQuery("from Student");
			// Create Query object.
			Query<T> query = session.createQuery(hql);
			// Execute query.
			List<T> ts = query.getResultList();
			// for (T emp : ts) {
			// System.out.println(emp);
			// }
			// Commit data.
			session.getTransaction().commit();
			return ts.isEmpty() ? Collections.emptyIterator() : ts.iterator();
		} catch (Exception e) {
			e.printStackTrace();
			// Rollback in case of an error occurred.
			session.getTransaction().rollback();
		}
		return Collections.emptyIterator();
	}

	@Override
	public T outOne(Class<T> clazz) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.getTransaction().begin();
			// Criteria criteria = session.createCriteria(clazz);
			// // 添加条件
			// criteria.add(Restrictions.eq("id", 1));
			//
			// // 查询全部，没有sql语句
			// List<User> list = criteria.list();
			//
			Query<T> query = session.createQuery(command);
			if (parameter != null || !parameter.isEmpty())
				query.setParameter(0, parameter);
			List<T> resultList = query.getResultList();
			session.getTransaction().commit();
			return resultList.isEmpty() ? null : resultList.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return null;
	}

	@Override
	public Boolean replaceAll(Iterator<T> objects) {
		Boolean result = false;
		while (objects.hasNext()) {
			result &= this.replaceOne(objects.next());
		}
		return result;
	}

	@Override
	public Boolean replaceOne(T object) {
		try {
			Session session = sessionFactory.openSession();
			session.beginTransaction();
			session.update(object);
			// session.save(object);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void setSearChCommand(String command) {
		this.command = command;
	}

	@Override
	public Boolean deleteOne(T object) {
//		System.err.println("delete target " + object);
		Session session = sessionFactory.getCurrentSession();
		/*
		 * in case object is not persistent, try to insert this object, if
		 * insert success, which means there is no match key in database
		 */
		if (this.inOne(object)) {
			session.getTransaction().begin();
			session.delete(object);
			session.getTransaction().commit();
			return false;
		}
		try {
			session.getTransaction().begin();
			/*
			 * delete ONLY WORK if object is persistent, if it is not
			 * persistent, delete() WILL NOT throw any exception
			 */
			session.delete(object);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return false;
		} finally {
			session.close();
		}
	}

	@Override
	public void setParameter(String parameter) {
		this.parameter = parameter;
	}
}