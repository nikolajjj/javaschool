package com.tsystems.dao.implementation;

import com.tsystems.dao.api.GenericDAO;
import com.tsystems.exception.CTCExecption;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.lang.reflect.ParameterizedType;
import java.util.List;

/**
 *
 * @param <E>
 * @param <ID>
 */
@SuppressWarnings("unchecked")
public abstract class GenericDAOImpl<E, ID> implements GenericDAO<E, ID> {
    private static final Logger LOG = Logger.getLogger(GenericDAOImpl.class);

    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public GenericDAOImpl() {
        ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class<E>) type.getActualTypeArguments()[0];
    }

    /**
     *
     * @param e
     */
    @Override
    public void add(E e) {
            entityManager.persist(e);
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public E findById(ID id) {
        return entityManager.find(entityClass, id);
//      return entityManager.getReference(entityClass, id);
    }

    /**
     *
     * @param e
     */
    @Override
    public void update(E e) {
        entityManager.merge(e);
    }

    /**
     *
     * @param e
     */
    @Override
    public void delete(E e) {
        entityManager.remove(e);
    }

    /**
     *
     * @return
     */
    @Override
    public List<E> getAll() {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
