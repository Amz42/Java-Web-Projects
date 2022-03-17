package com.amz42.studentsystem.service;

import com.amz42.studentsystem.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    StudentServiceImpl studentServiceImpl;

    @BeforeEach
    void setUp() {
        studentServiceImpl = new StudentServiceImpl(studentRepository);
    }

    @Test
    @DisplayName("should return all the students")
    void getAllStudents() {
        studentServiceImpl.getAllStudents();
        Mockito.verify(studentRepository).findAll();
    }
}