package com.biz.lesson.dao.student;


import com.biz.lesson.model.student.Student;
import com.biz.lesson.vo.student.StudentVo;

import java.util.List;

public interface StudentDao {
    List<Student> listByCondition(StudentVo vo);
}
