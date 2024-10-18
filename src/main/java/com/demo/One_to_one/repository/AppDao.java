package com.demo.One_to_one.repository;

import com.demo.One_to_one.entity.Address;
import com.demo.One_to_one.entity.Student;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class AppDao {

    private final EntityManager entityManager;

    @Transactional
    public Student saveStudent(Student student) {
        return entityManager.merge(student);
    }

    public Student getStudentByRollNo(int rollNo) {
        return entityManager.find(Student.class, rollNo);
    }

    public List<Student> getAllStudents() {
        List<Student> students = entityManager.createQuery("from Student").getResultList();
        return students;
    }

    @Transactional
    public String updateStudent(Student student, int rollNo) {
        Student existingStudent = getStudentByRollNo(rollNo);

        if (existingStudent != null) {
            // Retain the original address ID
            Address updatedAddress = student.getAddress();
            updatedAddress.setAddressId(existingStudent.getAddress().getAddressId());

            // Retain original roll number and admission date
            student.setRollNo(existingStudent.getRollNo());
            student.setAdmissionDate(existingStudent.getAdmissionDate());

            // Set the updated address
            student.setAddress(updatedAddress);

            // Persist changes to the database
            entityManager.merge(student);  // This saves the changes to the database

            return "Student updated successfully";
        } else {
            return "Student not found";
        }
    }

    @Transactional
    public String deleteByRollNO(int rollNo) {
        Student student = getStudentByRollNo(rollNo);
        if (student != null) {
            entityManager.remove(student);
            return "Deleted successfully";
        } else {
            return "Not Found!!";
        }
    }

    public Address getStudentByAddressId(int addressId) {
        return entityManager.find(Address.class, addressId);
    }

    @Transactional
    public Student updateAddressById(int rollNo, Address address) {
        Student student=entityManager.find(Student.class, rollNo);
        if(student != null){
            student.setAddress(address);
            return entityManager.merge(student);
        }else{
            return null;
        }
    }

    @Transactional
    public String deleteAddressById(int addressId) {
        Address address= entityManager.find(Address.class, addressId);
        if(address != null){
            Student student=address.getStudent();
            student.setAddress(null);
            entityManager.remove(address);
            return "Deleted Successfully";
        }else{
            return "not found!!";
        }
    }
}
