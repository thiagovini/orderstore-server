package com.ts.gio.orderstore.controller;

import com.ts.gio.orderstore.controller.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(path = "api/user")
public interface UserController {

    @GetMapping(path = "/getAllUser")
    ResponseEntity<List<UserResponse>> getAllUser();
}
