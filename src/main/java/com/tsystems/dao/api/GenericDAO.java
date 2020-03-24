package com.tsystems.dao.api;

import java.util.List;

/**
 *
 * @param <E>
 * @param <ID>
 */
public interface GenericDAO<E, ID> {
    /**
     *
     * @param e
     */
    void add(E e);

    /**
     *
     * @param id
     * @return
     */
    E findById(ID id);

    /**
     *
     * @param e
     */
    void update(E e);

    /**
     *
     * @param e
     */
    void delete(E e);

    /**
     *
     * @return
     */
    List<E> getAll();
}
