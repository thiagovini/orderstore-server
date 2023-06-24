package com.ts.gio.orderstore.controller.response;

import com.ts.gio.orderstore.entity.Role;
import com.ts.gio.orderstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private Long id;

    private String firstName;

    private String secondName;

    private String username;

    private String contactNumber;

    private String email;

    private boolean status;

    private Role role;

}
