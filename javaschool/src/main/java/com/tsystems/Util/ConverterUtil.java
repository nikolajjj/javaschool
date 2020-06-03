package com.tsystems.Util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsystems.dto.OrderDTO;
import com.tsystems.dto.MyOrderDTO;
import com.tsystems.dto.StatisticsCountDTO;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

public class ConverterUtil {
    private static final Logger log = Logger.getLogger(ConverterUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    public ConverterUtil() {
    }

    public static String parseJson(List<?> inputList) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(out, inputList);
            return new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String parseJson(Integer temp) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(out, temp);
            return new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static String parseJson(StatisticsCountDTO temp) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(out, temp);
            return new String(out.toByteArray());
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
            return null;
        }
    }

    public static MyOrderDTO parseOrderDTO(String json) {
        MyOrderDTO myOrderDTO = null;
        try {
            myOrderDTO = objectMapper.readValue(json, MyOrderDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return myOrderDTO;
    }

    public static OrderDTO parseCargoDTOLIst(String json) {
        OrderDTO orderDTO = null;
        try {
            orderDTO = objectMapper.readValue(json, OrderDTO.class);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            e.printStackTrace();
            return null;
        }
        return orderDTO;
    }
}
