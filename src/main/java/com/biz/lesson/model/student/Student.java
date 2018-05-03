package com.biz.lesson.model.student;

import com.biz.lesson.model.Persistent;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.model.score.Score;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "stu_student")
public class Student extends Persistent {

    @Column(length = 40)
    private String name;

    @Column
    private Integer studentNumber ;

    @Column(length = 1)
    private String gender;

    @Column
    private Date birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    private Grade grade;
    @Formula("(select avg(u.score) from stu_student_subject_score u where u.student_id = id)")
    private Double avgScore;
   /* @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stu_student_subject_score", joinColumns = {
            @JoinColumn(name = "student_id", referencedColumnName = "id")},
            inverseJoinColumns = {
            @JoinColumn(name = "subject_id", referencedColumnName = "id")})
    private List<Subject> subject;*/

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "student")
    private List<Score> scoreList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }



    public Date getBirthday() {
        return birthday;

    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

  /*  public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }*/
    public List<Score> getScoreList() {
            return scoreList;
        }

    public void setScoreList(List<Score> scoreList) {
            this.scoreList = scoreList;
        }

    public Double getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(Integer studentNumber) {
        this.studentNumber = studentNumber;
    }
}
