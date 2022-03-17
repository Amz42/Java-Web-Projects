package com.amz42.studentsystem.controller;

import com.amz42.studentsystem.model.Student;
import com.amz42.studentsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/student")
@CrossOrigin
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/add")
    public String add(@RequestBody Student student){
        studentService.save(student);
        return "New Student is added";
    }

    @GetMapping("/getAll")
    public List<Student> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> get(@PathVariable Integer id){
        try{
            Student student = studentService.get(id);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e){
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> put(@RequestBody Student student, @PathVariable Integer id){
        try{
            Student existingStudent = studentService.get(id);
            student.setId(existingStudent.getId());
            studentService.save(student);
            return new ResponseEntity<Student>(student, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<Student>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Integer id){
        studentService.deleteById(id);
        return "Deleted Student with id " + id;
    }
}


