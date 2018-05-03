package com.biz.lesson.web.controller.student;


import com.biz.lesson.business.grade.GradeService;
import com.biz.lesson.exception.BusinessAsserts;
import com.biz.lesson.model.grade.Grade;
import com.biz.lesson.vo.student.GradeVo;
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
import java.util.List;

@Controller
@RequestMapping("/student/grade")
public class GradeController extends BaseController {
    protected final Logger logger = LoggerFactory.getLogger(GradeController.class);
    @Autowired
    private GradeService gradeService;

    @RequestMapping("/list")
    @PreAuthorize("hasAuthority('OPT_STUDENT_GRADE_LIST')")
    public ModelAndView list() throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/grade/list");
        List<Grade> grades = gradeService.list();
        modelAndView.addObject("grades", grades);
        return modelAndView;
    }

    @RequestMapping("/add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_GRADE_ADD')")
    public ModelAndView add(HttpServletRequest request) throws Exception {

        ModelAndView modelAndView = new ModelAndView("student/grade/add");
        modelAndView.addObject("cmd", "add");
        addReferer(request);
        return modelAndView;
    }

    @RequestMapping("/save_add")
    @PreAuthorize("hasAuthority('OPT_STUDENT_GRADE_ADD')")
    public ModelAndView save_add(GradeVo vo, BindingResult result, HttpServletRequest request) throws Exception {

        Grade grade = new Grade();
        copyProperties(vo, grade);

        gradeService.saveOrUpdate(grade);
        return referer(request);
    }

    @RequestMapping("/edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_GRADE_EDIT')")
    public ModelAndView edit(String id, HttpServletRequest request) throws Exception {
        ModelAndView modelAndView = new ModelAndView("student/grade/add");

        Grade grade = gradeService.getGrade(id);
        BusinessAsserts.exists(grade, id);

        modelAndView.addObject("grade", grade);
        modelAndView.addObject("cmd", "edit");
        addReferer(request);
        return modelAndView;
    }
    @RequestMapping("/save_edit")
    @PreAuthorize("hasAuthority('OPT_STUDENT_GRADE_EDIT')")
    public ModelAndView save_edit(GradeVo vo, BindingResult result, HttpServletRequest request) throws Exception {

        Grade grade = gradeService.getGrade(vo.getId());
        BusinessAsserts.exists(grade, vo.getId());

        copyProperties(vo, grade);

        gradeService.saveOrUpdate(grade);
        return referer(request);
    }
    @RequestMapping("/delete")
    @PreAuthorize("hasAuthority('OPT_STUDENT_GRADE_DELETE')")
    @ResponseBody
    public Boolean delete(@RequestParam("id") String id) {
        try {
            gradeService.delete(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
