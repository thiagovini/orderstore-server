package com.ts.gio.orderstore.utils;

import com.ts.gio.orderstore.controller.response.UserResponse;
import com.ts.gio.orderstore.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderStoreUtils {

    public OrderStoreUtils(){}

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus status) {
        return new ResponseEntity<String>("{\"message\":\""+ responseMessage + "\"}", status);
    }

    public static ResponseEntity<String> getResponseEntity(String responseMessage, HttpStatus status, String token) {
        return new ResponseEntity<String>("{\"message\":\""+ responseMessage + "\"} \n{\"token\": \"" + token +"\"}", status);
    }
}
