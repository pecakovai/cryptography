package com.example.cryptography.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CourseController {

    @GetMapping("/courses")
    public String getCourse(){
        return "courses";
    }
}
