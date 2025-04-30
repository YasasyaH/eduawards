package com.school.eduawards.controller;

import com.school.eduawards.entity.YearDetails;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.YearDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private YearDetailsService yearDetailsService;

    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @GetMapping("/")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String authenticate(Model model) {
        List<YearDetails> list = yearDetailsService.getAll();
        model.addAttribute("years", list);
        model.addAttribute("semesters", semesterDetailsService.getAll());
        return "dashboard";
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
