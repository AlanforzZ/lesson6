package com.biz.lesson.business.grade;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.grade.GradeRepository;
import com.biz.lesson.model.grade.Grade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional()
public class GradeService extends BaseService {
    private static final Logger logger = LoggerFactory.getLogger(GradeService.class);
    @Autowired
    private GradeRepository gradeRepository;

    public List<Grade> list() {
        return gradeRepository.findAll();
    }
    public Grade getGrade(String id) {
        return  gradeRepository.findOne(id);
    }

    public void saveOrUpdate(Grade grade) {
        gradeRepository.save(grade);
    }

    public void delete(String id) {
        gradeRepository.delete(id);
    }
}
