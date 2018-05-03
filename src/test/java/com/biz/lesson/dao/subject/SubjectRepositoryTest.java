package com.biz.lesson.dao.subject;

import com.biz.lesson.model.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class SubjectRepositoryTest {
    @Autowired
    SubjectRepository subjectRepository;
    @Test
    public void findByIdNotIn() {
        List list = new ArrayList();
        list.add("4028098162ff96430162ff9c46fb0003");
        List<Subject> byIdNotIn = subjectRepository.findByIdNotIn(list);
    }
}