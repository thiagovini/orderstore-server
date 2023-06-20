package com.ts.gio.orderstore.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class OrderStoreUtils {

    public OrderStoreUtils(){}

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus status) {
        return new ResponseEntity<String>("{\"message\":\""+ responseMessage + "\"", status);
    }
}
