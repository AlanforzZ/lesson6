package com.biz.lesson.business.student;

import com.biz.lesson.dao.student.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class StudentServiceTest {
    @Autowired
    private StudentRepository studentRepository;
    @Test
    public void list() {
        studentRepository.findAll();
    }
}