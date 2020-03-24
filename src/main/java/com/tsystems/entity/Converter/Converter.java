package com.tsystems.entity.Converter;

import com.tsystems.dao.api.CargoDAO;
import com.tsystems.dto.*;
import com.tsystems.entity.*;

import java.util.List;
import java.util.ArrayList;

public class Converter {
    /**
     *
     * @param user
     * @return
     */
    public static UserDTO getUserDto(User user) {
        return new UserDTO(user.getId(), user.getUsername());
    }

    /**
     * Returns List of userDTO from list user entity
     *
     * @param userList userList
     * @return list userDTO
     */
    public static List<UserDTO> getUserDtos(List<User> userList) {
        List<UserDTO> userDtosResultList = new ArrayList<>();
        userList.forEach(user -> userDtosResultList.add(Converter.getUserDto(user)));
        return userDtosResultList;
    }

    public static CargoDTO getCargoDto(Cargo cargo) {
        City city_to = cargo.getCity_to(),
                city_from = cargo.getCity_from()/*,
                currentWagonCity = cargo.getOrder().getWagon().getCurrent_city()*/;
        CityDTO cityDTO_from = new CityDTO(city_from.getId(), city_from.getName(), city_from.getLongitude(), city_from.getLatitude()),
                cityDTO_to = new CityDTO(city_to.getId(), city_to.getName(), city_to.getLongitude(), city_to.getLatitude())/*,
                currentWagonCityDTO = new CityDTO(currentWagonCity.getId(), currentWagonCity.getName(), currentWagonCity.getLongitude(), currentWagonCity.getLatitude())*/;
        /*Order order = cargo.getOrder();
        Wagon wagon = cargo.getOrder().getWagon();
        WagonDTO wagonDTO = new WagonDTO(wagon.getId(), wagon.getCar_plate(), wagon.getDriver_shift_count(), wagon.getCapacity(),
                wagon.getState(), currentWagonCityDTO);*/
//        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getOrder_number(), order.getStatus(), wagonDTO);
        System.out.println(cargo.getStatus().toString());
        return new CargoDTO(cargo.getId(), cargo.getDescription(), cargo.getWeight(), cargo.getStatus().toString(), cityDTO_from, cityDTO_to/*, orderDTO*/);
    }

    public static List<CargoDTO> getCargoDtos(List<Cargo> cargoList) {
        List<CargoDTO> cargoDTOResultList = new ArrayList<>();
//        System.out.println(cargoList.size());
//        cargoList.forEach(cargo -> cargoDTOResultList.add(Converter.getCargoDto(cargo)));
        for(Cargo cargo : cargoList) {
            cargoDTOResultList.add(Converter.getCargoDto(cargo));
        }
        return cargoDTOResultList;
    }
}
