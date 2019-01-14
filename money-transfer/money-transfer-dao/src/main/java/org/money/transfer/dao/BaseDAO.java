package org.money.transfer.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

import org.money.transfer.entity.BaseEntity;

@Stateless
public class BaseDAO<T extends BaseEntity, ID> implements Serializable {

	private static final long serialVersionUID = -414407724016080901L;

	@PersistenceContext(unitName = "persistenceUnit")
	protected EntityManager entityManager;

	@SuppressWarnings("rawtypes")
	private Class entityClass;

	protected EntityManager getEntityManager() {
		return this.entityManager;
	}

	public void setEntityClass(final Class<?> entityClass) {
		this.entityClass = entityClass;
	}

	@SuppressWarnings("unchecked")
	public Class<T> getEntityClass() {
		if (this.entityClass == null) {
			this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
					.getActualTypeArguments()[0];
		}
		return this.entityClass;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T find(final ID id) {
		return getEntityManager().find(this.getEntityClass(), id);
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public T getReference(final ID id) {
		return getEntityManager().getReference(this.getEntityClass(), id);
	}

	public void delete(final Object primaryKey) {
		Object ref = null;
		ref = getEntityManager().getReference(this.getEntityClass(), primaryKey);
		getEntityManager().remove(ref);

	}

	public T update(final T t) {
		getEntityManager().merge(t);
		return t;

	}

	public void insert(final T t) {
		getEntityManager().persist(t);
		getEntityManager().flush();

	}

	public void insert(final List<T> list) {
		for (final T entity : list) {
			getEntityManager().persist(entity);
		}
		getEntityManager().flush();
	}

	@SuppressWarnings("unchecked")
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public List<T> findAll() {
		return getEntityManager().createQuery("Select entity FROM " + this.getEntityClass().getSimpleName() + " entity")
				.getResultList();
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Integer findCount() {
		@SuppressWarnings("rawtypes")
		final List result = getEntityManager()
				.createQuery("Select count(entity) FROM " + this.getEntityClass().getSimpleName() + " entity")
				.getResultList();

		final Integer count = (Integer) result.get(0);

		return count;
	}

	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public Long findCountLong() {
		@SuppressWarnings("rawtypes")
		final List result = getEntityManager()
				.createQuery("Select count(entity) FROM " + this.getEntityClass().getSimpleName() + " entity")
				.getResultList();

		final Long count = (Long) result.get(0);

		return count;
	}

	@SuppressWarnings("unchecked")
	public T executeSingleNamedQuery(final String namedQuery, final Map<String, Object> params) {
		try {
			final Query query = getEntityManager().createNamedQuery(namedQuery);
			for (final String param : params.keySet()) {
				query.setParameter(param, params.get(param));
			}
			query.setMaxResults(1);
			return (T) query.getSingleResult();

		} catch (final NoResultException e) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> executeNamedQuery(final String namedQuery, final Map<String, Object> params) {
		final Query query = getEntityManager().createNamedQuery(namedQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> executeQuery(final String strQuery, final Map<String, Object> params) {
		final Query query = getEntityManager().createQuery(strQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query.getResultList();
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public Vector<Object[]> executeNamedQueryForObject(final String namedQuery, final Map<String, Object> params) {
		final Query query = getEntityManager().createNamedQuery(namedQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return (Vector<Object[]>) query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> executeNamedQueryForList(final String namedQuery, final Map<String, Object> params) {
		final Query query = getEntityManager().createNamedQuery(namedQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public T executeSingleQuery(final String namedQuery, final Map<String, Object> params) {
		try {
			final Query query = getEntityManager().createQuery(namedQuery);
			for (final String param : params.keySet()) {
				query.setParameter(param, params.get(param));
			}
			return (T) query.getSingleResult();
		} catch (final NoResultException e) {
			return null;
		}
	}

	public List executeNamedQueryNative(final String nativeQuery, final Map<String, Object> params) {
		final Query query = getEntityManager().createNamedQuery(nativeQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> executeNamedQuery(final String namedQuery, final Map<String, Object> parametros,
			final Integer currentPage, final Integer pageSize) {
		final Query query = getEntityManager().createNamedQuery(namedQuery);

		if (currentPage != null) {
			query.setFirstResult(currentPage);
		}

		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}

		if (parametros != null) {
			for (final String chave : parametros.keySet()) {
				query.setParameter(chave, parametros.get(chave));
			}
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> executeCriteria(final CriteriaQuery criteriaQuery, final Integer currentPage,
			final Integer pageSize) {
		return executeCriteria(criteriaQuery, currentPage, pageSize, null);
	}

	@SuppressWarnings("unchecked")
	public List<T> executeCriteria(final CriteriaQuery criteriaQuery, final Integer currentPage, final Integer pageSize,
			final ConcurrentHashMap<String, Object> params) {
		final Query query = getEntityManager().createQuery(criteriaQuery);

		if (currentPage != null) {
			query.setFirstResult(currentPage);
		}

		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}

		if (params != null) {
			for (Entry<String, Object> kv : params.entrySet()) {
				query.setParameter(kv.getKey(), kv.getValue());
			}
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<T> executeNativeQuery(final String strQuery, final Map<String, Object> parametros,
			final Integer currentPage, final Integer pageSize) {
		final Query query = getEntityManager().createNativeQuery(strQuery);

		if (currentPage != null) {
			query.setFirstResult(currentPage);
		}

		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}

		if (parametros != null) {
			for (final String chave : parametros.keySet()) {
				query.setParameter(chave, parametros.get(chave));
			}
		}

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public Object executeCriteria(final CriteriaQuery criteriaQuery) {
		final Query query = getEntityManager().createQuery(criteriaQuery);
		return query.getSingleResult();
	}

	public List executeNativeQuery(final String strQuery, final Map<String, Object> params) {
		final Query query = getEntityManager().createNativeQuery(strQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query.getResultList();
	}

	public void executeUpdateNamedQuery(final String namedQuery, final Map<String, Object> params) {

		final Query query = getEntityManager().createNamedQuery(namedQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		query.executeUpdate();

	}

	public List executeNamedQueryNative(final String strQuery, final Map<String, Object> parametros,
			final Integer currentPage, final Integer pageSize) {

		final Query query = getEntityManager().createNamedQuery(strQuery);

		if (currentPage != null) {
			query.setFirstResult(currentPage);
		}

		if (pageSize != null) {
			query.setMaxResults(pageSize);
		}

		if (parametros != null) {
			for (final String chave : parametros.keySet()) {
				query.setParameter(chave, parametros.get(chave));
			}
		}

		return query.getResultList();
	}

	public void executeUpdateNativeQuery(final String strQuery, final Map<String, Object> params) {
		final Query query = getEntityManager().createNativeQuery(strQuery);
		for (final String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		query.executeUpdate();
	}
}