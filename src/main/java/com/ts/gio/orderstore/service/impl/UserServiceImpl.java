package com.ts.gio.orderstore.service.impl;

import com.ts.gio.orderstore.config.JwtService;
import com.ts.gio.orderstore.constants.OrderStoreConstants;
import com.ts.gio.orderstore.controller.request.AuthenticationRequest;
import com.ts.gio.orderstore.controller.request.RegisterRequest;
import com.ts.gio.orderstore.entity.User;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private final AuthenticationManager authManager;

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
                    var jwtToken = jwtService.generateToken(userFromRequest);
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
            var jwtToken = jwtService.generateToken(user);
            return OrderStoreUtils.getResponseEntity(OrderStoreConstants.USER_VALIDATED, HttpStatus.OK, jwtToken);
        } catch (Exception exception) {
            logger.info(OrderStoreConstants.SOMETHING_WENT_WRONG + "{" + exception.getMessage() +"}");
        }
        return OrderStoreUtils.getResponseEntity(OrderStoreConstants.INVALID_USER, HttpStatus.UNAUTHORIZED);
    }

}
