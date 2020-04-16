package com.sgu.springTask.mvc.controller;

import java.util.HashMap;
import java.util.Map;

public class MoneyConverter {

    private static Map<String, Double> convertMap = new HashMap<>();

    static {
        convertMap.put("rub", 1.);
        convertMap.put("usd", 30.);
        convertMap.put("eur", 42.);
    }

    public static double convert(double amount, String from, String to) {
        return amount * (convertMap.get(from) / convertMap.get(to));
    }
}
