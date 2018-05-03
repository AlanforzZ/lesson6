package com.biz.lesson.dao.score;

import com.biz.lesson.model.score.Score;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/application-content.xml")
public class ScoreRepositoryTest {
    @Autowired
    ScoreRepository scoreRepository;
    @Test
    public void getScoreByStudentAndSubject() {
        Score score = scoreRepository.getScore("4028098162ff84600162ff8b80ed0005", "4028098162ff96430162ff9c5b470004");
    }
}