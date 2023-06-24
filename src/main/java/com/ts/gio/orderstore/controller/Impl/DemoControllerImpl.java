package com.ts.gio.orderstore.controller.Impl;

import com.ts.gio.orderstore.controller.DemoController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoControllerImpl implements DemoController {


    @Override
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("Hello from secured endpoint!");
    }
}
