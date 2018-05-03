package com.biz.lesson.web.controller.student;

import com.biz.lesson.business.grade.GradeService;
import com.biz.lesson.business.score.ScoreService;
import com.biz.lesson.business.student.StudentService;
import com.biz.lesson.business.subject.SubjectService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.model.score.Score;
import com.biz.lesson.model.student.Student;
import com.biz.lesson.model.subject.Subject;
import com.biz.lesson.vo.student.StudentVo;
import com.biz.lesson.web.controller.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/student/student")
public class StudentController extends BaseController {
    protected final Logger logger = LoggerFactory.getLogger(StudentController.class);
    @Autowired
    private StudentService studentService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private SubjectService subjectService;
    @Autowired
    private ScoreService scoreService;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/list");
        List<Student> students = studentService.list();
        modelAndView.addObject("students", students);
        return modelAndView;
    }

    @RequestMapping("/search")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_LIST')")
    public ModelAndView search(StudentVo vo , BindingResult result,HttpServletRequest request) throws Exception {
        if (request.getParameter("startDate") != null && !request.getParameter("startDate").equals("")) {
            vo.setStartDate(Date.valueOf(request.getParameter("startDate")));
        }
        if (request.getParameter("endDate") != null && !request.getParameter("endDate").equals("")) {
            vo.setEndDate(Date.valueOf(request.getParameter("endDate")));
        }
        ModelAndView modelAndView = new ModelAndView("student/student/list");
        List<Student> students = studentService.listByCondition(vo);
        modelAndView.addObject("students", students);
        modelAndView.addObject("vo", vo);

        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {
        List<Grade> grades = gradeService.list();
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        modelAndView.addObject("cmd", "add");
        modelAndView.addObject("grades", grades);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
    public ModelAndView save_add(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        if (request.getParameter("birthday") != null && !request.getParameter("birthday").equals("")) {
            vo.setBirthday(Date.valueOf(request.getParameter("birthday")));
        }
        String gradeId = request.getParameter("gradeId");
        Grade grade = gradeService.getGrade(gradeId);

        Student student = new Student();
        copyProperties(vo, student);
        student.setGrade(grade);
        studentService.saveOrUpdate(student);
        return referer(request);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/student/add");
        List<Grade> grades = gradeService.list();
        Student student = studentService.getStudent(id);
        BusinessAsserts.exists(student, id);

        modelAndView.addObject("student", student);
        modelAndView.addObject("cmd", "edit");
        modelAndView.addObject("grades", grades);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView save_edit(StudentVo vo, BindingResult result, HttpServletRequest request) throws Exception {
        if (request.getParameter("birthday") != null && !request.getParameter("birthday").equals("")) {
            vo.setBirthday(Date.valueOf(request.getParameter("birthday")));
        }
        Student student = studentService.getStudent(vo.getId());
        BusinessAsserts.exists(student, vo.getId());

        copyProperties(vo, student);

        studentService.saveOrUpdate(student);
        return referer(request);
    }

    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String id) {
        try {
            studentService.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @RequestMapping("/select")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_SELECT')")
    public ModelAndView select(String id, HttpServletRequest request) throws Exception {

        Student student = studentService.getStudent(id);
        BusinessAsserts.exists(student, id);
        List<Subject> notSelectSubjects = subjectService.getNotSelectSubject(student);
        ModelAndView modelAndView = new ModelAndView("student/student/select");

        modelAndView.addObject("student", student);
        modelAndView.addObject("cmd", "select");
        modelAndView.addObject("notSelectSubjects", notSelectSubjects);
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_select")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_SELECT')")
    public ModelAndView save_select(StudentVo vo ,HttpServletRequest request) throws Exception {

        Student student = studentService.getStudent(vo.getId());
        List<Score> scores = student.getScoreList();
        //拿到学生添加过的课程
        List<String> list = new ArrayList<>();
        scores.forEach(o->
                list.add(o.getSubject().getId())
        );
        BusinessAsserts.exists(student, vo.getId());
        String[] subjectIds = vo.getNew_subjects();
            if (subjectIds!=null&&subjectIds.length>0){
                for (int i = 0; i < subjectIds.length; i++){
                    //判断学生对应的可能是否存在，存在，跳过
                    Boolean isExist = scoreService.isExist(vo.getId() ,subjectIds[i]);
                    if (isExist){
                        continue;
                    }else{//不存在，添加
                        Score score = new Score();
                        String subjectId = subjectIds[i];
                        Subject subject = subjectService.getSubject(subjectId);
                        score.setStudent(student);
                        BusinessAsserts.exists(student, vo.getId());
                        score.setSubject(subject);
                        scoreService.save(score);
                    }
                }
                List<String> ids = Arrays.asList(subjectIds);
                //删除退选的
                for (int i = 0;i<list.size();i++) {
                    boolean isContain = ids.contains(list.get(i));
                    if(!isContain){
                        scoreService.deleteByStudentIdAndSubjectId(vo.getId(),list.get(i));
                    }
                }
            }else {
                scoreService.deleteAll();
            }


            return referer(request);
    }
    @RequestMapping("/saveScore")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_ADD')")
    public ModelAndView add(String id,HttpServletRequest request) throws Exception {
        Student student = studentService.getStudent(id);
        BusinessAsserts.exists(student, id);
        ModelAndView modelAndView = new ModelAndView("student/student/addScore");
        modelAndView.addObject("student", student);
        modelAndView.addObject("cmd", "addScore");
        addReferer(request);
        return modelAndView;
    }
    @RequestMapping("/saveScore_addScore")
    @PreAuthorize("hasAuthority('OPT_STUDENT_STUDENT_EDIT')")
    public ModelAndView save_edit(String id, HttpServletRequest request) throws Exception {

        Student student = studentService.getStudent(id);
        BusinessAsserts.exists(student, id);
        Map<String, String[]> maps = request.getParameterMap();
        for (Map.Entry<String, String[]> map:maps.entrySet()
             ) {
            String subjectId = map.getKey();
            String scoreString = map.getValue()[0];
            if(student.getId().equals(scoreString)){
                 continue;
            }
            int scoreInt = 0;
            try{
                scoreInt = Integer.parseInt(scoreString);
            }catch (Exception e){
                e.printStackTrace();
            }
            Boolean isExist = scoreService.isExist(student.getId() ,subjectId);
            if (isExist){
                Score score = scoreService.getScoreBystudentIdAndSubjectId(student.getId(), subjectId);
                score.setScore(scoreInt);
                scoreService.save(score);
            }
        }

        return referer(request);
    }

}
