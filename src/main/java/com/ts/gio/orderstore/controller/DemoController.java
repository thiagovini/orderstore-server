package com.ts.gio.orderstore.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(path = "api/demo")
public interface DemoController {

    @GetMapping
    ResponseEntity<String> sayHello();

    @GetMapping(path = "/getAllUser")
    ResponseEntity<String> getAllUser();
}

