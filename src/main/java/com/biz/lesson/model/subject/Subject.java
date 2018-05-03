package com.biz.lesson.model.subject;

import com.biz.lesson.model.Persistent;
import com.biz.lesson.model.score.Score;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "stu_subject")
public class Subject extends Persistent {
    @Column
    private String name;

    /*@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "stu_student_subject_score", joinColumns = {
            @JoinColumn(name = "subject_id", referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "student_id", referencedColumnName = "id")})
    private List<Student> student;*/

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "subject")
    private List<Score> scoreList;
    @Formula("(SELECT AVG(c.score) " +
            "from stu_student_subject_score c " +
            "WHERE c.subject_id = id )")
    private Double avgScore;

    public Subject() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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
}
