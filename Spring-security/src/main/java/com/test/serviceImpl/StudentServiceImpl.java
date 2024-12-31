package com.test.serviceImpl;

import com.test.model.Student;
import com.test.repository.StudentRepository;
import com.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Student saveData(Student student) {
        String newPass = passwordEncoder.encode(student.getPassword());
        student.setPassword(newPass);
        Student std = studentRepository.save(student);
        return std;
    }
}
