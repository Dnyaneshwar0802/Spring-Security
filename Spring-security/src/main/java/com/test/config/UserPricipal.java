package com.test.config;

import com.test.model.Student;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public class UserPricipal implements UserDetails {
    private Student student;


    public UserPricipal(Student student) {
       this.student=student;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return student.getPassword();
    }

    @Override
    public String getUsername() {
        return student.getUsername();
    }
}
