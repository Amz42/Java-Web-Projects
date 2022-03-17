package com.amz42.studentsystem.service;

import com.amz42.studentsystem.model.Student;
import com.amz42.studentsystem.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student save(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student get(Integer studentId) {
        return studentRepository.findById(studentId).get();
    }

    @Override
    public void deleteById(Integer studentId) {
        studentRepository.deleteById(studentId);
    }
}
