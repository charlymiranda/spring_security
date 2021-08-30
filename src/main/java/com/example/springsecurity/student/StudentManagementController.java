package com.example.springsecurity.student;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/management/api/v1/students")
public class StudentManagementController {

    private static final List<Student> STUDENT_LIST = Arrays.asList(
            new Student(1, "Carlos"),
            new Student(2, "Daiana"),
            new Student(3, "Felipe")
    );

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ADMINTRAINEE')")//hasRole('ROLE_' hasAnyrole('ROLE_') hasAuthority('permission') hasAnyAuthority('permission')
    public List<Student> getAllStudents(){
        System.out.println("getAllStudents");
        return STUDENT_LIST;
    }
    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student){
        System.out.println("registerNewStudent");

        System.out.println(student);
    }
    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(Integer studentId){
        System.out.println("deleteStudent");
        System.out.println(studentId);
    }
    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(@PathVariable Integer studentId,@RequestBody Student student){
        System.out.println("updateStudent");

        System.out.println(String.format("%s %s", student, studentId));
    }
}
