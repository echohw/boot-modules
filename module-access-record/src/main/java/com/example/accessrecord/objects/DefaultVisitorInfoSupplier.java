package com.example.accessrecord.objects;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by AMe on 2020-07-21 20:40.
 */
public class DefaultVisitorInfoSupplier implements VisitorInfoSupplier<String> {

    @Override
    public String get(HttpServletRequest request) {
        return "anonym";
    }
}
