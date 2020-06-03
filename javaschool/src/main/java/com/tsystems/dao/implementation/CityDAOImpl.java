package com.tsystems.dao.implementation;

import com.tsystems.dao.api.CityDAO;
import com.tsystems.dao.api.GenericDAO;
import com.tsystems.entity.City;
import org.springframework.stereotype.Repository;

@Repository
public class CityDAOImpl extends GenericDAOImpl<City, Integer> implements CityDAO {

}
