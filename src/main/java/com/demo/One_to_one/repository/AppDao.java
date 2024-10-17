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

}
