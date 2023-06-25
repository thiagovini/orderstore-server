package com.ts.gio.orderstore.service.impl;

import com.ts.gio.orderstore.config.JwtAuthenticationFilter;
import com.ts.gio.orderstore.config.JwtService;
import com.ts.gio.orderstore.constants.OrderStoreConstants;
import com.ts.gio.orderstore.controller.request.ActiveDisableRequest;
import com.ts.gio.orderstore.controller.request.AuthenticationRequest;
import com.ts.gio.orderstore.controller.request.RegisterRequest;
import com.ts.gio.orderstore.controller.request.UpdateUserRequest;
import com.ts.gio.orderstore.controller.response.UserResponse;
import com.ts.gio.orderstore.entity.User;
import com.ts.gio.orderstore.exception.UserNotFoundException;
import com.ts.gio.orderstore.repository.UserRepository;
import com.ts.gio.orderstore.service.UserService;
import com.ts.gio.orderstore.utils.OrderStoreUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

    private final JwtAuthenticationFilter jwtAuthFilter;

    @Override
    public ResponseEntity<String> register(RegisterRequest request) {
        logger.info("Inside signup{" + request.toString() + "}");
        try {
            if (request.validateRegister()) {
                Optional<User> user = userRepository.findByEmail(request.getEmail());
                if (user.isEmpty()) {
                    request.setPassword(encoder.encode(request.getPassword()));
                    User userFromRequest = request.toUser();
                    userRepository.save(userFromRequest);
                    var jwtToken = jwtService.generateToken(userFromRequest, userFromRequest.toRoleMap());
                    return OrderStoreUtils.getResponseEntity(OrderStoreConstants.SUCCESSFULLY_REGISTERED, HttpStatus.CREATED, jwtToken);
                } else {
                    return OrderStoreUtils.getResponseEntity(OrderStoreConstants.EMAIL_ALREADY_EXIST, HttpStatus.BAD_REQUEST);
                }
            } else {
                return OrderStoreUtils.getResponseEntity(OrderStoreConstants.INVALID_DATE, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            logger.info(OrderStoreConstants.SOMETHING_WENT_WRONG + "{" + ex.getMessage() +"}");
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> authenticate(AuthenticationRequest request) {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            if (!user.isActive()) {
                return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_NOT_ACTIVE, HttpStatus.UNAUTHORIZED);
            }
            var jwtToken = jwtService.generateToken(user, user.toRoleMap());
            return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_VALIDATED, HttpStatus.OK, jwtToken);
        } catch (Exception exception) {
            logger.info(OrderStoreConstants.SOMETHING_WENT_WRONG + "{" + exception.getMessage() +"}");
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.INVALID_USER, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<List<UserResponse>> getAllUser() {
        try {
            if (jwtAuthFilter.isAdmin()) {
                List<UserResponse> userResponseList =  userRepository
                        .findAll()
                        .stream()
                        .map(User::toUserResponse)
                        .collect(Collectors.toList());
                return ResponseEntity.status(HttpStatus.OK).body(userResponseList);
            }
        } catch (Exception exception){
            logger.info(OrderStoreConstants.SOMETHING_WENT_WRONG + "{" + exception.getMessage() +"}");
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).eTag(OrderStoreConstants.UNAUTHORIZED_USER).body(new ArrayList<>());
    }

    @Override
    public ResponseEntity<String> updateUser(UpdateUserRequest userRequest) {
        try {
            User user = userRepository.findById(userRequest.getId()).orElseThrow(UserNotFoundException::new);
            user.updateUser(userRequest);
            userRepository.save(user);
            return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_UPDATED, HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            logger.info(OrderStoreConstants.USER_NOT_FOUND + "{" + exception.getMessage() +"}");
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> activeUser(ActiveDisableRequest activeDesableRequest) {
        try {
            if (jwtAuthFilter.isAdmin()) {
                User user = userRepository.findById(activeDesableRequest.getId()).orElseThrow(UserNotFoundException::new);
                user.activateUser();
                userRepository.save(user);
                return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_ACTIVATE, HttpStatus.OK);
            } else {
                return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_IS_NOT_ADMIN, HttpStatus.OK);
            }
        } catch (UserNotFoundException exception) {
            logger.info(OrderStoreConstants.USER_NOT_FOUND + "{" + exception.getMessage() +"}");
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> disableUser(ActiveDisableRequest activeDesableRequest) {
        try {
            User user = userRepository.findById(activeDesableRequest.getId()).orElseThrow(UserNotFoundException::new);
            user.disableUser();
            userRepository.save(user);
            return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_DISABLE, HttpStatus.OK);
        } catch (UserNotFoundException exception) {
            logger.info(OrderStoreConstants.USER_NOT_FOUND + "{" + exception.getMessage() +"}");
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_NOT_FOUND, HttpStatus.BAD_REQUEST);
    }


}
