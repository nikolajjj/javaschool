package com.tsystems.dao.api;

import com.tsystems.entity.Cargo;

import java.util.List;

/**
 * CRUD and specific operations of Cargo entity
 */
public interface CargoDAO extends GenericDAO<Cargo, Integer> {
    public List<Cargo> getCargoesByOrderId(Integer orderId);
}
