package com.ts.gio.orderstore.controller;

import com.ts.gio.orderstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping(path = "/user")
public interface UserController {

    @PostMapping(path = "signup")
    public ResponseEntity<String> signUp(@RequestBody(required = true) UserDTO requestMap);

    @GetMapping(path = "/passwordValidate")
    ResponseEntity<Boolean> passwordValidate(@RequestParam String username, @RequestParam String password);
}
