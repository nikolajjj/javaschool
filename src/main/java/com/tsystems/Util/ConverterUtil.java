package com.tsystems.Util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ConverterUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();
    public ConverterUtil() {
    }

    public static String parseJson(List<?> inputList) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            objectMapper.writeValue(out, inputList);
            System.out.println("fine");
            return new String(out.toByteArray());
        } catch (IOException e) {
            System.out.println("not fine");
            e.printStackTrace();
//            log.error(e.getMessage(), e);
            return null;
        }
    }
}
