package com.ts.gio.orderstore.dto;

import com.ts.gio.orderstore.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private final String firstName;
    private final String secondName;
    private final String username;
    private final String contactNumber;
    private final String email;
    private String password;

    public User toUser() {
        return User.builder().firstName(this.firstName).secondName(this.secondName).username(this.username).contactNumber(this.contactNumber).email(this.email).password(this.password).build();
    }

    public String toString() {
        return this.firstName + " " + this.secondName + ", " + this.contactNumber + ", " + this.email + ", " + this.password;
    }

    public boolean validateSignUp(){
        return !this.firstName.isBlank() && !this.secondName.isBlank() && !this.username.isBlank() && !this.contactNumber.isBlank() && !this.email.isBlank()
                && !this.password.isBlank();

    }

}
