package com.gym.helper.helper;

import java.util.HashMap;
import java.util.Map;

public class ExceptionHelper {
    public static Map<String, String> extractException(String cause) {
        String[] causes = cause.split("\\[");
        Map<String, String> exceptions = new HashMap<>();
        if (causes.length > 1) {
            if (causes.length > 2) {
                exceptions.put(causes[0], causes[1]);
            } else {
                exceptions.put(causes[0], causes[2]);
            }
        }
        return exceptions;
    }
}