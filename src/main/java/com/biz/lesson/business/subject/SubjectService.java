package com.biz.lesson.business.subject;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.subject.SubjectRepository;
import com.biz.lesson.model.score.Score;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional()
public class SubjectService extends BaseService {
    @Autowired
    private SubjectRepository subjectRepository;

    public List<Subject> list() {
        return subjectRepository.findAll();
    }

    public void saveOrUpdate(Subject subject) {
        subjectRepository.save(subject);
    }

    public Subject getSubject(String id) {
        return subjectRepository.findOne(id);
    }

    public List<Subject> getNotSelectSubject(Student student) {
        List ids = new ArrayList();
        List<Score> scoreList = student.getScoreList();
        if (scoreList.size()==0){
            return subjectRepository.findAll();
        }else {
            for (Score score : scoreList) {
                String id = score.getSubject().getId();
                ids.add(id);
            }
        }
        return subjectRepository.findByIdNotIn(ids);
    }

    public void delete(String id) {
        subjectRepository.delete(id);
    }
}
