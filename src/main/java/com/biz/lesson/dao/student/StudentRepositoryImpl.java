package com.biz.lesson.dao.student;

import com.biz.lesson.dao.common.CommonJpaRepositoryBean;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.vo.student.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class StudentRepositoryImpl extends CommonJpaRepositoryBean<Student, String> implements StudentDao {
	@Autowired
	public StudentRepositoryImpl(EntityManager em) {
		super(Student.class, em);
	}
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public List<Student> listByCondition(StudentVo vo) {
		List<Student> resultList = null;
		Specification querySpecifi = new Specification<Student>() {
			@Override
			public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {

				List<Predicate> predicates = new ArrayList<>();
				if(null != vo.getStartDate()){
					predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("birthday"),vo.getStartDate() ));
				}
				if(null != vo.getEndDate()){
					predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("birthday"),vo.getEndDate() ));
				}
				if(null != vo.getStudentNumber()){
					predicates.add(criteriaBuilder.equal(root.get("studentNumber"), vo.getStudentNumber()));
				}
				if(null != vo.getName()){
					predicates.add(criteriaBuilder.like(root.get("name"), "%"+vo.getName()+"%"));
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		resultList =  studentRepository.findAll(querySpecifi);
		return resultList;
	}
}