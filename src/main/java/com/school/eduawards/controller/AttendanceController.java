package com.school.eduawards.controller;

import com.school.eduawards.dto.AttendanceRecord;
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
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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

    @PostMapping("/save")
    public String saveAttendance(@RequestParam Map<String, String> allParams,
                                 @RequestParam("year") int year,
                                 @RequestParam("className") String className) {

        // Fetch yearDetails and semesterDetails
        YearDetails yearDetails = yearDetailsService.getById(year);
        SemesterDetails semesterDetails = semesterDetailsService.findCurrentSemester(); // assume you have this



        // Current Month
        int month = LocalDateTime.now().getMonthValue();

        // Group attendance by student
        Map<Integer, List<AttendanceRecord>> studentAttendanceMap = new HashMap<>();

        for (Map.Entry<String, String> entry : allParams.entrySet()) {
            String key = entry.getKey(); // Example: attendance[2][15]
            if (key.startsWith("attendance")) {
                String[] parts = key.replaceAll("[^0-9]+", " ").trim().split(" ");
                int studentId = Integer.parseInt(parts[0]);
                int date = Integer.parseInt(parts[1]);

                AttendanceRecord record = new AttendanceRecord();
                record.setDate(String.format("%02d", date)); // '01', '02', etc.
                record.setPresent(1); // Checkbox is checked = present

                studentAttendanceMap
                        .computeIfAbsent(studentId, k -> new ArrayList<>())
                        .add(record);
            }
        }

        // Save Attendance for each student
        for (Map.Entry<Integer, List<AttendanceRecord>> entry : studentAttendanceMap.entrySet()) {
            Integer studentId = entry.getKey();
            List<AttendanceRecord> records = entry.getValue();

            // Save one StudentAttendance record per student
            StudentAttendance attendance = new StudentAttendance();
            attendance.setYearDetails(yearDetails);
            attendance.setSemesterDetails(semesterDetails);
            attendance.setMonth(month);
            attendance.setType("Monthly"); // You can change if you want
            attendance.setStatus("Active");

            // Convert List<AttendanceRecord> to one AttendanceRecord
            AttendanceRecord attendanceRecord = new AttendanceRecord();
            attendanceRecord.setDate(LocalDateTime.now().toString());
            attendanceRecord.setPresent(records.size());

            attendance.setAttendanceRecord(attendanceRecord);
            attendance.setEnterDate(LocalDateTime.now());
            attendance.setEnterUser("admin"); // Or fetch logged in user

            studentAttendanceService.save(attendance);
        }

        return "redirect:/attendance"; // Go back to page
    }

}
