package com.tsystems.dao.implementation;

import com.tsystems.dao.api.CargoDAO;
import com.tsystems.entity.Cargo;
import org.springframework.stereotype.Repository;

@Repository
public class CargoDAOImpl extends GenericDAOImpl<Cargo, Integer> implements CargoDAO {
}
