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
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/load-dashboard")
    public String editSemesterDetails(@PathVariable Integer id, Model model) {
        List<YearDetails> years = yearDetailsService.getAll();
        List<SemesterDetails> semesters = semesterDetailsService.getAll();


        model.addAttribute("years", years);
        model.addAttribute("semesters", semesters);
        return "dashboard";
    }

    @GetMapping("/marks-dashboard")
    public String viewDashboard(@RequestParam(value = "yearIdIp", required = false) Integer yearIdIp,
                                @RequestParam(value = "semesterIdIp", required = false) Integer semesterIdIp,
                                Model model) {

        List<YearDetails> years = yearDetailsService.getAll();
        List<SemesterDetails> semesters = semesterDetailsService.getAll();


        model.addAttribute("years", years);
        model.addAttribute("semesters", semesters);
        model.addAttribute("selectedYear", yearIdIp);
        model.addAttribute("selectedSemester", semesterIdIp);


        Map<String, StudentMarks> topStudents = marksService.getTopStudentsBySubject(yearIdIp, semesterIdIp);
        model.addAttribute("topStudents", topStudents);


        return "dashboard";
    }
}
