package com.biz.lesson.model.grade;

import com.biz.lesson.model.Persistent;
import com.biz.lesson.model.student.Student;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "stu_grade")
public class Grade extends Persistent {
    @Column
    private String name;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "grade")
    private List<Student> students;

    @Formula("(SELECT AVG(c.score) from stu_student_subject_score c WHERE c.student_id IN(SELECT s.id from stu_student  s where s.grade_id = id))")
    private Double avgScore;

    public Grade() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }
}
