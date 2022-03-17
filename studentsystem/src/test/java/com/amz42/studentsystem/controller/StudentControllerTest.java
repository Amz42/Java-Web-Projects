package com.amz42.studentsystem.controller;

import com.amz42.studentsystem.model.Student;
import com.amz42.studentsystem.service.StudentServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
@WithMockUser
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentServiceImpl studentService;

    private String CONTROLLER_ENDPOINT = "/student";
    private Integer mockStudentId = 1;
    private String mockStudentName = "Aman";
    private String mockStudentAddress = "India";
    private Student mockStudent = new Student(mockStudentId, "Aman", "India");
    private String mockStudentJson =
            "{\"id\":" + mockStudentId + ",\"name\":" + mockStudentName + ",\"address\":" + mockStudentAddress +"}";

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
    }

    @Test
    void getAllStudents() {
    }

    @Test
    void get() throws Exception {
        Mockito.when(studentService.get(mockStudentId)).thenReturn(mockStudent);
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(CONTROLLER_ENDPOINT + "/" + mockStudentId).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "{id:" + mockStudentId + ",name:" + mockStudentName + ",address:" + mockStudentAddress + "}";
        JSONAssert.assertEquals(expected, result.getResponse().getContentAsString(), false);
    }

    @Test
    void put() {
        //
    }

    @Test
    void delete() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete(CONTROLLER_ENDPOINT + "/" + mockStudentId).accept(MediaType.TEXT_PLAIN);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expected = "Deleted Student with id " + mockStudentId;
        Assertions.assertEquals(result.getResponse().getContentAsString(), expected);
    }
}