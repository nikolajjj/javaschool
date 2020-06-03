package com.tsystems.controller.validation;

import javax.validation.Valid;
import java.util.regex.Pattern;

public class Validator {
    public static final Pattern PLATE_PATTERN = Pattern.compile("^[A-Za-z]{2}?\\d{5}$");

    private Validator() {

    }

    public static boolean isPlateValid(String expression) {
        return PLATE_PATTERN.matcher(expression).matches();
    }
}
