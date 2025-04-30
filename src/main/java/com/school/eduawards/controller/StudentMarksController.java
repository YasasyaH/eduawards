package com.school.eduawards.controller;

import com.school.eduawards.entity.SemesterDetails;
import com.school.eduawards.entity.Student;
import com.school.eduawards.entity.StudentMarks;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.StudentMarksService;
import com.school.eduawards.service.StudentService;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/student-marks")
public class StudentMarksController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private YearDetailsService yearDetailsService;

    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @Autowired
    private StudentMarksService studentMarksService;

    @GetMapping
    public String studentMarks(Model model) {
        model.addAttribute("studentMarks", new StudentMarks());
        model.addAttribute("marksList", studentMarksService.getAll());
        model.addAttribute("yearList", yearDetailsService.getAll());
        model.addAttribute("semesterList", semesterDetailsService.getAll());
        model.addAttribute("studentList", studentService.getAll());
        return "student-marks-form";
    }
    @PostMapping("/save")
    public String saveStudentMarks(@ModelAttribute("studentMarks") StudentMarks studentMarks) {
        studentMarks.setEnterDate(LocalDateTime.now());
        studentMarks.setEnterUser("admin");

        studentMarksService.save(studentMarks);
        return "redirect:/student-marks";
    }
    @GetMapping("/student-marks/edit/{id}")
    public String editStudentMarks(@PathVariable Integer id, Model model) {
        StudentMarks existing = studentMarksService.getById(id);
        model.addAttribute("studentMarks", existing);
        model.addAttribute("marksList", studentMarksService.getAll());
        model.addAttribute("yearList", yearDetailsService.getAll());
        model.addAttribute("semesterList", semesterDetailsService.getAll());
        return "student_marks_form";
    }

    @GetMapping("/student-marks/delete/{id}")
    public String deleteStudentMarks(@PathVariable Integer id) {
        studentMarksService.delete(id);
        return "redirect:/student-marks";
    }

}
