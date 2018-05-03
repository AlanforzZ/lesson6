package com.biz.lesson.business.score;

import com.biz.lesson.business.BaseService;
import com.biz.lesson.dao.score.ScoreRepository;
import com.biz.lesson.model.score.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional()
public class ScoreService extends BaseService {
    @Autowired
    ScoreRepository scoreRepository;
    public void save(Score score) {
        scoreRepository.save(score);
    }

    public Boolean isExist(String studentId, String subjectId) {
        Score score = scoreRepository.getScore(studentId, subjectId);
        if (score!=null){
            return true;
        }
        return false;
    }
    public Score getScoreBystudentIdAndSubjectId(String studentId, String subjectId){
        return scoreRepository.getScore(studentId, subjectId);
    }
    public void deleteByStudentIdAndSubjectId(String studentId, String subjectId) {
        Score score = scoreRepository.getScore(studentId, subjectId);
        scoreRepository.deleteByScoreId(score.getId());
//        scoreRepository.delete(score);
    }

    public void deleteAll() {
        scoreRepository.deleteAllScore();
    }
}
