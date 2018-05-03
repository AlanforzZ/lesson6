package com.biz.lesson.dao.score;

import com.biz.lesson.dao.common.CommonJpaRepository;
import com.biz.lesson.dao.grade.GradeDao;
import com.biz.lesson.model.score.Score;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ScoreRepository extends CommonJpaRepository<Score, String>, GradeDao {

    @Query(value = "select * from stu_student_subject_score u where u.student_id = ? and u.subject_id = ?", nativeQuery = true)
    Score getScore(String student, String subject);

    @Modifying
    @Query(value = "delete from Score u where u.id = ?1")
    void deleteByScoreId(String id);
    @Modifying
    @Query(value = "delete from Score where 1=1")
    void deleteAllScore();

    /*@Query(value = "delete from Score u where u.id = ?1")
    void deleteOne(String id);*/
}
