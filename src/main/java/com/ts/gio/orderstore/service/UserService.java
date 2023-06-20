package com.ts.gio.orderstore.service;

import com.ts.gio.orderstore.dto.UserDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;
import java.util.logging.Logger;

public interface UserService {

    Logger logger = Logger.getLogger(String.valueOf(UserService.class));

    ResponseEntity<String> singUp(UserDTO userDTO);
}
