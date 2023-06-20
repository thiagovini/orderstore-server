package com.ts.gio.orderstore.controller;

import com.ts.gio.orderstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserController {

    @PostMapping(path = "signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) UserDTO requestMap);
}
