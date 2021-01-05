package com.example.springsecuritydemo.contoller;

import com.example.springsecuritydemo.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/students")
public class StudentController {
    private final static Logger LOG = LoggerFactory.getLogger(StudentController.class);

    private List<Student> students = Arrays.asList(
            new Student(1, "Ayush"),
            new Student(2, "Nikita"),
            new Student(3, "Subhankar")
    );

    @GetMapping(path = "{studentId}")
    public Student getStudent(@PathVariable Integer studentId) {
        LOG.info("fetching details for ID : " + studentId);
        return students.stream().
                filter((student) -> studentId == student.getId()).
                findFirst().
                orElseThrow(() -> new IllegalStateException());
    }
}
