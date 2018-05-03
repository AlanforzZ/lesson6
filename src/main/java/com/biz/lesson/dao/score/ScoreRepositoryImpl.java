package com.biz.lesson.dao.score;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.score.Score;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;

public class ScoreRepositoryImpl extends CommonJpaRepositoryBean<Score, String> implements ScoreDao {
	@Autowired
	public ScoreRepositoryImpl(EntityManager em) {
		super(Score.class, em);
	}
}