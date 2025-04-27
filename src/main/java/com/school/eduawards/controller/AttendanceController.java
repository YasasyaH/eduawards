package com.school.eduawards.controller;

import com.school.eduawards.entity.Student;
import com.school.eduawards.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String showAttendancePage(Model model) {
        model.addAttribute("students", null);
        model.addAttribute("dates", null);
        return "attendance-form";
    }

    @GetMapping("/search")
    public String searchStudents(@RequestParam("year") int year,
                                 @RequestParam("className") String className,
                                 Model model) {

        List<Student> students = studentService.findByYearAndClass(year, className);


        YearMonth yearMonth = YearMonth.now();
        List<Integer> dates = IntStream.rangeClosed(1, yearMonth.lengthOfMonth())
                .boxed()
                .collect(Collectors.toList());

        model.addAttribute("students", students);
        model.addAttribute("dates", dates);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedClass", className);

        return "attendance-form";
    }

}
