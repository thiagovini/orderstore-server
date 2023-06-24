package com.ts.gio.orderstore.controller;

import com.ts.gio.orderstore.controller.request.AuthenticationRequest;
import com.ts.gio.orderstore.controller.request.RegisterRequest;
import com.ts.gio.orderstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path = "api/auth/user")
public interface AuthenticationUserController {

    @PostMapping(path = "signup")
    public ResponseEntity<String> register(@RequestBody(required = true) RegisterRequest request);

    @GetMapping(path = "/authenticate")
    ResponseEntity<String> authenticate(@RequestBody(required = true) AuthenticationRequest request);
}
