package com.biz.lesson.business.student;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.student.StudentRepository;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.vo.student.StudentVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional()
public class StudentService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> list() {
        return studentRepository.findAll();
    }

    public void saveOrUpdate(Student student) {
        studentRepository.save(student);
    }

    public Student getStudent(String id) {
        return  studentRepository.findOne(id);
    }

    public void delete(String id) {
        studentRepository.delete(id);
    }

    public List<Student> listByCondition(StudentVo vo) {
        return studentRepository.listByCondition(vo);
    }
}
