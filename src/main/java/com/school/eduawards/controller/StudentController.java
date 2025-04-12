package com.school.eduawards.controller;

import com.school.eduawards.entity.Student;
import com.school.eduawards.entity.StudentAddress;
import com.school.eduawards.service.StudentAddressService;
import com.school.eduawards.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentAddressService studentAddressService;

    @GetMapping
    public String studentDetails(Model model) {
        List<Student> studentList = studentService.getAll();
        List<StudentAddress> studentAddressList = studentAddressService.getAll();
        model.addAttribute("studentList", studentList);
        model.addAttribute("studentAddressList", studentAddressList);

        Student student = new Student();
        //student.setStudentAddresses(new StudentAddress());

        model.addAttribute("student", student);
        return "student-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("student") Student student) {
        if (student.getId() == null) {
            student.setEnterDate(LocalDateTime.now());
            student.setEnterUser("admin");
        }
        studentService.save(student);
        return "redirect:/student";
    }

    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.getById(id);
        List<Student> list = studentService.getAll();

        List<StudentAddress> studentAddressList = studentAddressService.getAll();
        ;
        model.addAttribute("studentAddressList", studentAddressList);

        model.addAttribute("student", student);
        model.addAttribute("studentList", list);

        return "student";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Integer id) {
        studentService.delete(id);
        return "redirect:/student";
    }

}
