package com.ts.gio.orderstore.entity;

import com.ts.gio.orderstore.controller.request.UpdateUserRequest;
import com.ts.gio.orderstore.controller.response.UserResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
@Builder
@DynamicUpdate
@DynamicInsert
@Table(name = "user_table")
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    @Column(name = "username")
    private String username;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private boolean active;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserResponse toUserResponse() {
        return UserResponse.builder()
                .id(this.id)
                .firstName(this.firstName)
                .secondName(this.secondName)
                .username(this.username)
                .contactNumber(this.contactNumber)
                .email(this.email)
                .role(this.role)
                .status(this.active)
                .build();
    }

    public Map<String, String> toRoleMap() {
        Map<String, String> role = new HashMap<>();
        role.put("role", this.getRole().toString());
        return role;
    }

    public void updateUser(UpdateUserRequest updateRequest) {
        if (updateRequest.getFirstName() != null) this.firstName = updateRequest.getFirstName();
        if (updateRequest.getSecondName() != null) this.secondName = updateRequest.getSecondName();
        if (updateRequest.getUsername() != null) this.username = updateRequest.getUsername();
        if (updateRequest.getContactNumber() != null) this.contactNumber = updateRequest.getContactNumber();
    }

    public void activateUser() {
        this.active = true;
    }

    public void disableUser() {
        this.active = false;
    }
}
