package com.test.restcontroller;

import com.test.model.Student;
import com.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/studentRestcontroller")
public class StudentRestcontroller {
    @Autowired
StudentService studentService;
    @PostMapping("/saveData")
    public Student saveData(@RequestBody Student student){
        System.out.println("Enter");
        System.out.println(student);
        System.out.println("Exit");
        Student std=studentService.saveData(student);
return std;
    }

    @GetMapping("/welcome")
    public String getMessage(){
        return "WELCOME TO SECURED URL";
    }
}
