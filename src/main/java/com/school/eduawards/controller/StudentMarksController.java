package com.school.eduawards.controller;

import com.school.eduawards.entity.SemesterDetails;
import com.school.eduawards.entity.StudentMarks;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.StudentService;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/student-marks")
public class StudentMarksController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private YearDetailsService yearDetailsService;

    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @GetMapping
    public String studentMarks(Model model) {
        model.addAttribute("studentMarks", new StudentMarks());
        model.addAttribute("yearList", yearDetailsService.getAll());
        model.addAttribute("semesterList", semesterDetailsService.getAll());
        return "student-marks-form";
    }
}
