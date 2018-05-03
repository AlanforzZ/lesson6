package com.biz.lesson.dao.subject;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.subject.Subject;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface SubjectRepository extends CommonJpaRepository<Subject, String>, SubjectDao {
    List<Subject> findByIdNotIn(Collection ids);
}
