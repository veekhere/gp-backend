package com.veekhere.rentrate.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

public class JsonService {
    public static String toJson(Object object) {
        return new Gson().toJson(object);
    }

    public static Map<String, Object> toMap(Object object) {
        final Gson gson = new Gson();
        final String json = gson.toJson(object);
        return gson.fromJson(json, new TypeToken<Map<String, Object>>() {}.getType());
    }
}
