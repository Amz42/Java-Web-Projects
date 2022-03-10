package com.amz42.studentsystem.service;

import com.amz42.studentsystem.model.Student;

import java.util.List;

public interface StudentService {
    public Student save(Student student);
    public List<Student> getAllStudents();
}
