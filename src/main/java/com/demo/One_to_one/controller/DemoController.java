package com.demo.One_to_one.controller;

import com.demo.One_to_one.entity.Student;
import com.demo.One_to_one.repository.AppDao;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DemoController {

    private AppDao appDao;

    @PostMapping("/save")
    public Student saveStudent(@RequestBody Student student){
        return appDao.saveStudent(student);
    }

    @GetMapping("/getStudent/{rollNo}")
    public Student getStudent(@PathVariable int rollNo){
        return appDao.getStudentByRollNo(rollNo);
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudent(){
        return appDao.getAllStudents();
    }

    @PutMapping("/update/{rollNo}")
    public String updateById(@RequestBody Student student, @PathVariable int rollNo) {
        return appDao.updateStudent(student, rollNo);
    }

}
