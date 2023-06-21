package com.ts.gio.orderstore.service.impl;

import com.ts.gio.orderstore.constants.OrderStoreConstants;
import com.ts.gio.orderstore.dto.UserDTO;
import com.ts.gio.orderstore.entity.User;
import com.ts.gio.orderstore.repository.UserRepository;
import com.ts.gio.orderstore.service.UserService;
import com.ts.gio.orderstore.utils.OrderStoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseEntity<String> singUp(UserDTO userDTO) {
        logger.info("Inside signup{" + userDTO.toString() + "}");
        try {
            if (validateSignUp(userDTO)){
                User user = userRepository.findByEmail(userDTO.getEmail());
                if (Objects.isNull(user)){
                    userRepository.save(userDTO.toUser());
                    return OrderStoreUtils.getResponseEntity(OrderStoreConstants.SUCCESSFULLY_REGISTERED, HttpStatus.CREATED);
                } else {
                    return OrderStoreUtils.getResponseEntity(OrderStoreConstants.EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
                }
            } else {
                return OrderStoreUtils.getResponseEntity(OrderStoreConstants.INVALID_DATE, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.info(OrderStoreConstants.SUCCESSFULLY_REGISTERED + "{" + ex.getMessage() +"}");
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private boolean validateSignUp(UserDTO userDTO){
        return !userDTO.getName().isBlank() && !userDTO.getContactNumber().isBlank() && !userDTO.getEmail().isBlank()
                && !userDTO.getPassword().isBlank();

    }
}
