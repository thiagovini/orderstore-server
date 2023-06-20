package com.ts.gio.orderstore.dto;

import com.ts.gio.orderstore.entity.User;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class UserDTO {

    private String name;
    private String contactName;
    private String email;
    private String password;

    public String getName() {
        return name;
    }

    public String getContactName() {
        return contactName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User toUser() {
        //TODO
        return null;
    }
}
