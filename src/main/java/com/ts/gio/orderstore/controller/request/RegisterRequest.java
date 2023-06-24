package com.ts.gio.orderstore.controller.request;

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
public class RegisterRequest {

    private String name;
    private String username;
    private String contactNumber;
    private String email;
    private String password;

    public User toUser() {
        return User.builder()
                .name(this.name)
                .username(this.username)
                .contactNumber(this.contactNumber)
                .email(this.email)
                .password(this.password)
                .role(Role.USER)
                .build();
    }

    public String toString() {
        return this.name + ", " + this.contactNumber + ", " + this.email + ", " + this.password;
    }

    public boolean validateRegister(){
        return !this.name.isBlank() && !this.username.isBlank() && !this.contactNumber.isBlank() && !this.email.isBlank()
                && !this.password.isBlank();

    }
}