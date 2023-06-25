package com.ts.gio.orderstore.service;

import com.ts.gio.orderstore.controller.request.ActiveDisableRequest;
import com.ts.gio.orderstore.controller.request.AuthenticationRequest;
import com.ts.gio.orderstore.controller.request.RegisterRequest;
import com.ts.gio.orderstore.controller.request.UpdateUserRequest;
import com.ts.gio.orderstore.controller.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.logging.Logger;

public interface UserService {

    Logger logger = Logger.getLogger(String.valueOf(UserService.class));

    ResponseEntity<String> register(RegisterRequest request);

    ResponseEntity<String> authenticate(AuthenticationRequest request);

    ResponseEntity<List<UserResponse>> getAllUser();

    ResponseEntity<String> updateUser(UpdateUserRequest userRequest);

    ResponseEntity<String> activeUser(ActiveDisableRequest activeDesableRequest);

    ResponseEntity<String> disableUser(ActiveDisableRequest activeDesableRequest);
}
