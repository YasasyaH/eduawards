package com.school.eduawards.controller;

import com.school.eduawards.entity.SemesterDetails;
import com.school.eduawards.entity.Student;
import com.school.eduawards.entity.StudentAttendance;
import com.school.eduawards.entity.YearDetails;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.StudentAttendanceService;
import com.school.eduawards.service.StudentService;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private YearDetailsService yearDetailsService;

    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @Autowired
    private StudentAttendanceService studentAttendanceService;


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

        List<Integer> dates = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            dates.add(i);
        }

        model.addAttribute("students", students);
        model.addAttribute("dates", dates);
        model.addAttribute("selectedYear", year);
        model.addAttribute("selectedClass", className);

        return "attendance-form";
    }

    @PostMapping("/save")
    public String saveAttendance(@RequestParam Map<String, String> allParams,
                                 @RequestParam("year") int year,
                                 @RequestParam("className") String className) {


        YearDetails yearDetails = yearDetailsService.getById(year);
        SemesterDetails semesterDetails = semesterDetailsService.findById(1);

        int month = LocalDateTime.now().getMonthValue();

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = entry.getKey();
            if (key.startsWith("attendance")) {
                String[] parts = key.replaceAll("[^0-9]+", " ").trim().split(" ");
                int studentId = Integer.parseInt(parts[0]);
                int date = Integer.parseInt(parts[1]);

                boolean present = entry.getValue().equals("on");

                // Create and save the attendance record
                StudentAttendance attendance = new StudentAttendance();
                attendance.setYearDetails(yearDetailsService.getById(1));
                attendance.setSemesterDetails(semesterDetails);
                attendance.setStudent(studentService.getById(studentId));
                attendance.setMonth(month);
                attendance.setType("Monthly");
                attendance.setStatus("Active");
                attendance.setPresent(present);
                attendance.setAttendanceDate(LocalDateTime.now());
                attendance.setEnterDate(LocalDateTime.now());
                attendance.setEnterUser("admin");

                studentAttendanceService.save(attendance);
            }
        }

        return "redirect:/attendance"; // Go back to the page after saving attendance
    }

}
