package com.demo.One_to_one.controller;

import com.demo.One_to_one.entity.Address;
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

    @DeleteMapping("/deleteStudent/{rollNo}")
    public String deleteById(@PathVariable int rollNo){
        String message=appDao.deleteByRollNO(rollNo);
        return message;
    }


    // address CRUD

    @PostMapping("/saveAddress")
    public Student saveAddress(@RequestBody Address address){
        Student student=address.getStudent();
        student.setAddress(address);
        return appDao.saveStudent(student);
    }

    @GetMapping("/getAddress/{addressId}")
    public Address getStudentAddress(@PathVariable int addressId ){
        return appDao.getStudentByAddressId(addressId);
    }

    @PutMapping("/updateAddress/{rollNo}")
    public Student updateAddress(@PathVariable int rollNo, @RequestBody Address address){
        return appDao.updateAddressById(rollNo, address);
    }

    @DeleteMapping("/deleteAddress/{addressId}")
    public String deleteAddress(@PathVariable int addressId){
        String message = appDao.deleteAddressById(addressId);
        return message;
    }
}
