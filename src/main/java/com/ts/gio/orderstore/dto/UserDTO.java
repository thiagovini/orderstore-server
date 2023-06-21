package com.ts.gio.orderstore.dto;

import com.ts.gio.orderstore.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private final String name;
    private final String contactNumber;
    private final String email;
    private String password;

    public User toUser() {
        return User.builder().name(this.name).contactNumber(this.contactNumber).email(this.email).password(this.password).build();
    }

    public String toString() {
        return this.name + ", " + this.contactNumber + ", " + this.email + ", " + this.password;
    }

}
