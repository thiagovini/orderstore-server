package com.ts.gio.orderstore.controller.Impl;

import com.ts.gio.orderstore.controller.UserController;
import com.ts.gio.orderstore.controller.request.ActiveDisableRequest;
import com.ts.gio.orderstore.controller.request.UpdateUserRequest;
import com.ts.gio.orderstore.controller.response.UserResponse;
import com.ts.gio.orderstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public ResponseEntity<List<UserResponse>> getAllUser() {
        return userService.getAllUser();
    }

    @Override
    public ResponseEntity<String> disableUser(ActiveDisableRequest activeDisableRequest) {
        return userService.disableUser(activeDisableRequest);
    }

    @Override
    public ResponseEntity<String> activeUser(ActiveDisableRequest activeDisableRequest) {
        return userService.activeUser(activeDisableRequest);
    }

    @Override
    public ResponseEntity<String> updateUser(UpdateUserRequest userRequest) {
        return userService.updateUser(userRequest);
    }
}
