package com.ts.gio.orderstore.controller.request;

import com.ts.gio.orderstore.entity.Role;
import com.ts.gio.orderstore.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {

    private Long id;
    private String firstName;
    private String secondName;
    private String username;
    private String contactNumber;

    public User toUser() {
        return User.builder()
                .id(this.id)
                .firstName(this.firstName)
                .secondName(this.secondName)
                .username(this.username)
                .contactNumber(this.contactNumber)
                .build();
    }

}
