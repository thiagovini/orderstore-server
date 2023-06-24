package com.ts.gio.orderstore.service;

import com.ts.gio.orderstore.controller.request.AuthenticationRequest;
import com.ts.gio.orderstore.controller.request.RegisterRequest;
import com.ts.gio.orderstore.controller.response.AuthenticationResponse;
import com.ts.gio.orderstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.logging.Logger;

public interface UserService {

    Logger logger = Logger.getLogger(String.valueOf(UserService.class));

    ResponseEntity<String> register(RegisterRequest request);

    ResponseEntity<String> authenticate(AuthenticationRequest request);
}
