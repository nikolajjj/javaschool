package com.tsystems.service.implementation;

import com.tsystems.dao.api.WagonDAO;
import com.tsystems.entity.Wagon;
import com.tsystems.service.api.WagonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 */
@Service
public class WagonServiceImpl implements WagonService {
    private WagonDAO wagonDAO;

    public WagonServiceImpl(WagonDAO wagonDAO) {
        this.wagonDAO = wagonDAO;
    }

    @Autowired
    public void setWagonDAO(WagonDAO wagonDAO) {
        this.wagonDAO = wagonDAO;
    }

    /**
     *
     * @param wagon
     */
    @Transactional
    public void addWagon(Wagon wagon) {
        wagonDAO.add(wagon);
    }

    /**
     *
     * @param id
     * @return
     */
    @Transactional
    public Wagon findWagonById(Integer id) {
        return wagonDAO.findById(id);
    }

    /**
     *
     * @param wagon
     */
    @Transactional
    public void updateWagon(Wagon wagon) {
        wagonDAO.update(wagon);
    }

    /**
     *
     * @param wagon
     */
    @Transactional
    public void deleteWagon(Wagon wagon) {
        wagonDAO.delete(wagon);
    }

    /**
     *
     * @return
     */
    @Transactional
    public List<Wagon> getAllWagons() {
        return wagonDAO.getAll();
    }
}
