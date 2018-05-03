package com.biz.lesson.dao.grade;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.model.grade.Grade;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeRepository extends CommonJpaRepository<Grade, String>, GradeDao {

}
