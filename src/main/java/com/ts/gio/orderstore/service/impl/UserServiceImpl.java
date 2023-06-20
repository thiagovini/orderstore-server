package com.ts.gio.orderstore.service.impl;

import com.ts.gio.orderstore.constants.OrderStoreConstants;
import com.ts.gio.orderstore.dto.UserDTO;
import com.ts.gio.orderstore.entity.User;
import com.ts.gio.orderstore.repository.UserRepository;
import com.ts.gio.orderstore.service.UserService;
import com.ts.gio.orderstore.utils.OrderStoreUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> singUp(UserDTO userDTO) {
        logger.info("Inside signup{" + userDTO.toString() + "}");
        if (validateSignUp(userDTO)){
            User user = userRepository.findByEmail(userDTO.getEmail());
            if (Objects.isNull(user)){
                userRepository.save(userDTO.toUser());
            } else {
                return OrderStoreUtils.getResponseEntity(OrderStoreConstants.EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
            }
        } else {
            return OrderStoreUtils.getResponseEntity(OrderStoreConstants.INVALID_DATE, HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    private boolean validateSignUp(UserDTO userDTO){
        return userDTO.getName().isBlank() && userDTO.getContactName().isBlank() && userDTO.getEmail().isBlank()
                && userDTO.getPassword().isBlank();

    }
}
