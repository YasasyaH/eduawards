package com.school.eduawards.controller;

import com.school.eduawards.entity.SemesterDetails;
import com.school.eduawards.entity.StudentMarks;
import com.school.eduawards.entity.YearDetails;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.StudentMarksService;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MarksDashboardController {

    @Autowired
    private StudentMarksService marksService;

    @Autowired
    private YearDetailsService yearDetailsService;

    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @GetMapping("/marks-dashboard")
    public String viewDashboard(@RequestParam(value = "yearId", required = false) Integer yearId,
                                @RequestParam(value = "semesterId", required = false) Integer semesterId,
                                Model model) {

        List<YearDetails> years = yearDetailsService.getAll();
        List<SemesterDetails> semesters = semesterDetailsService.getAll();

        model.addAttribute("years", years);
        model.addAttribute("semesters", semesters);
        model.addAttribute("selectedYear", yearId);
        model.addAttribute("selectedSemester", semesterId);

        if (yearId != null && semesterId != null) {
            Map<String, StudentMarks> topStudents = marksService.getTopStudentsBySubject(yearId, semesterId);
            model.addAttribute("topStudents", topStudents);
        }

        return "marks-dashboard";
    }
}
