package com.ts.gio.orderstore.controller.Impl;

import com.ts.gio.orderstore.constants.OrderStoreConstants;
import com.ts.gio.orderstore.controller.AuthenticationUserController;
import com.ts.gio.orderstore.controller.request.AuthenticationRequest;
import com.ts.gio.orderstore.controller.request.RegisterRequest;
import com.ts.gio.orderstore.controller.response.AuthenticationResponse;
import com.ts.gio.orderstore.dto.UserDTO;
import com.ts.gio.orderstore.service.UserService;
import com.ts.gio.orderstore.utils.OrderStoreUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthenticationUserControllerImpl implements AuthenticationUserController {

    private final UserService userService;

    @PostMapping(path = "/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request
    ) {
        return userService.register(request);
    }

    @PostMapping(path = "/authenticate")
    public ResponseEntity<String> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return userService.authenticate(request);
    }

}
