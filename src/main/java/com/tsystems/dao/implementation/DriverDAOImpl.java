package com.tsystems.dao.implementation;

import com.tsystems.dao.api.DriverDAO;
import com.tsystems.entity.Driver;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public class DriverDAOImpl extends GenericDAOImpl<Driver, Integer> implements DriverDAO {
}
