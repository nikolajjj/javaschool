package com.tsystems.service.implementation;

import com.tsystems.dao.api.DriverDAO;
import com.tsystems.dao.api.OrderDAO;
import com.tsystems.dao.api.WagonDAO;
import com.tsystems.dto.CityDTO;
import com.tsystems.dto.MyOrderDTO;
import com.tsystems.dto.StatisticsCountDTO;
import com.tsystems.dto.WagonDTO;
import com.tsystems.dto.enums.OrderStatus;
import com.tsystems.entity.Order;
import com.tsystems.entity.Wagon;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ScoreBoardSenderImpl implements com.tsystems.service.api.ScoreBoardSender {
    private WagonDAO wagonDAO;
    private OrderDAO orderDAO;
    private DriverDAO driverDAO;

    private final static Logger log = Logger.getLogger(ScoreBoardSenderImpl.class);

    @Autowired
    public ScoreBoardSenderImpl(WagonDAO wagonDAO, OrderDAO orderDAO, DriverDAO driverDAO) {
        this.wagonDAO = wagonDAO;
        this.orderDAO = orderDAO;
        this.driverDAO = driverDAO;
    }

    @Override
    @Transactional
    public List<MyOrderDTO> sendAllOrders() {
        List<MyOrderDTO> set = new ArrayList<>();
        List<Order> orders = orderDAO.getAll();
        for (Order order : orders) {
            try {
                // City
                Integer cityId = order.getWagon().getCurrent_city().getId();
                String name = order.getWagon().getCurrent_city().getName();
                Double longitude = order.getWagon().getCurrent_city().getLongitude();
                Double latitude = order.getWagon().getCurrent_city().getLatitude();
                CityDTO cityDTO = new CityDTO(cityId, name, longitude, latitude);

                // Wagon
                Integer wagonId = order.getWagon().getId();
                String car_plate = order.getWagon().getCar_plate();
                Integer driver_shift_count = order.getWagon().getDriver_shift_count();
                Integer capacity = order.getWagon().getCapacity();
                String wagonStatus = order.getWagon().getState().toString();
                WagonDTO wagonDTO = new WagonDTO(wagonId, car_plate, driver_shift_count, capacity, wagonStatus, cityDTO);

                // Order
                Integer orderId = order.getId();
                String order_number = order.getOrder_number();
                String orderStatus = order.getStatus().toString();
                set.add(new MyOrderDTO(orderId, order_number, orderStatus, wagonDTO));
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }

        }
        return set;
    }

    @Override
    @Transactional
    public StatisticsCountDTO sendStatistic() {
        Integer drivers = driverDAO.getAll().size();
        Integer freeDrivers = driverDAO.getFreeDrivers().size();
        Integer busyDrivers = drivers - freeDrivers;

        Integer wagons = wagonDAO.getAll().size();
        Integer disabledWagons = wagonDAO.getAllDisabledWagons().size();
        Integer busyWagons = this.getBusyWagonsCount();
        Integer freeWagons = wagons - busyWagons - disabledWagons;
        List<MyOrderDTO> orders = this.sendAllOrders();
        StatisticsCountDTO statistic = new StatisticsCountDTO(drivers, freeDrivers, busyDrivers,
                wagons, freeWagons, busyWagons, disabledWagons, orders);
        return statistic;
    }

    private Integer getBusyWagonsCount() {
        List<Wagon> resultSet = new ArrayList<>();
        List<Wagon> wagons = wagonDAO.getAll();
        List<Order> orders = orderDAO.getAll();
        orders.forEach(order -> {
            if (wagons.contains(order.getWagon()) && !order.getStatus().equals(OrderStatus.DONE)) {
                resultSet.add(order.getWagon());
            }
        });
        return resultSet.size();
    }
}
