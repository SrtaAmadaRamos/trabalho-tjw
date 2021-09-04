package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import util.JPAUtil;

public abstract class GenericDao<T> {
	
	protected final EntityManager manager = JPAUtil.getEntityManager();
	
	private final Class<T> clazz;
	
	public GenericDao(Class<T> clazz) {
		this.clazz = clazz;
	}
	
	public T getById(int id) {
		return manager.find(clazz, id);
	}
	
	public List<T> findAll() {
		String JPQL = "SELECT a FROM " + clazz.getSimpleName() + " a";
		TypedQuery<T> a = manager.createQuery(JPQL, clazz);
		return a.getResultList();
	}
	
	public void insert(T a) {
		manager.getTransaction().begin();
		manager.persist(a);
		manager.getTransaction().commit();
	}
	
	public void update(T a) {
		manager.getTransaction().begin();
		manager.merge(a);
		manager.getTransaction().commit();
	}
	
	public void delete(int id) {
		manager.remove(getById(id));
	}
	
	public void close() {
		manager.close();
	}
}
