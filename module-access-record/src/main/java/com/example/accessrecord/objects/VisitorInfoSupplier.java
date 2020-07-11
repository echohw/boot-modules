package com.example.accessrecord.objects;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by AMe on 2020-07-11 16:59.
 */
@FunctionalInterface
public interface VisitorInfoSupplier<T> {

    T get(HttpServletRequest request);
}
