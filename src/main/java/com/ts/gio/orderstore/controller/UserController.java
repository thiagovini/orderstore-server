package com.ts.gio.orderstore.controller;

import com.ts.gio.orderstore.controller.request.ActiveDisableRequest;
import com.ts.gio.orderstore.controller.request.UpdateUserRequest;
import com.ts.gio.orderstore.controller.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(path = "api/user")
public interface UserController {

    @GetMapping(path = "/getAllUser")
    ResponseEntity<List<UserResponse>> getAllUser();

    @PostMapping(path = "/disableUser")
    ResponseEntity<String> disableUser(@RequestBody(required = true) ActiveDisableRequest activeDisableRequest);

    @PostMapping(path = "/activeUser")
    ResponseEntity<String> activeUser(@RequestBody(required = true) ActiveDisableRequest activeDisableRequest);

    @PostMapping(path = "/updateUser")
    ResponseEntity<String> updateUser(@RequestBody UpdateUserRequest userRequest);

}
