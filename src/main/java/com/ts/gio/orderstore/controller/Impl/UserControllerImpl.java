package com.ts.gio.orderstore.controller.Impl;

import com.ts.gio.orderstore.constants.OrderStoreConstants;
import com.ts.gio.orderstore.controller.UserController;
import com.ts.gio.orderstore.dto.UserDTO;
import com.ts.gio.orderstore.service.UserService;
import com.ts.gio.orderstore.utils.OrderStoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserControllerImpl implements UserController {

    @Autowired
    private UserService userService;
    @Override
    public ResponseEntity<String> signUp(UserDTO requestMap) {
        try {
            return userService.singUp(requestMap);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
