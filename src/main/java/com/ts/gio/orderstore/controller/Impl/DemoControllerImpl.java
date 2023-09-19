package com.ts.gio.orderstore.controller.Impl;

import com.ts.gio.orderstore.controller.DemoController;
import com.ts.gio.orderstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoControllerImpl implements DemoController {

    private final UserService userService;

    @Override
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint!");
    }

    @Override
    public ResponseEntity<String> getAllUser() {
        userService.getAllUser();
        return ResponseEntity.ok("Hello from secured endpoint!");
    }
}
