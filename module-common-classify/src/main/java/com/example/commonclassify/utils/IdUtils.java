package com.example.commonclassify.utils;

import java.util.UUID;

/**
 * Created by AMe on 2020-07-08 02:15.
 */
public class IdUtils {

    public static String getId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}
