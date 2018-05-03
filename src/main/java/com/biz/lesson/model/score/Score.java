package com.biz.lesson.model.score;

import com.biz.lesson.model.Persistent;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.subject.Subject;

import javax.persistence.*;

@Entity()
@Table(name = "stu_student_subject_score")
public class Score extends Persistent {
    @Column
    private Integer score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }
}
