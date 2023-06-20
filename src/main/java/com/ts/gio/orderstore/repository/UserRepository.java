package com.ts.gio.orderstore.repository;

import com.ts.gio.orderstore.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

    User findByEmail(String email);
}
