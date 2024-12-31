package com.test.config;

import com.test.model.Student;
import com.test.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    StudentRepository studentRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
Student student = studentRepository.findByUsername(username);
if(student==null){
    System.out.println("is it null");
    throw new UsernameNotFoundException("USER NOT FOUND");
}
else {
    return new UserPricipal(student);
}

    }
}
