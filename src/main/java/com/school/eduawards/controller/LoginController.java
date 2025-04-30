package com.school.eduawards.controller;

import com.school.eduawards.entity.User;
import com.school.eduawards.entity.YearDetails;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.UserService;
import com.school.eduawards.service.YearDetailsService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private YearDetailsService yearDetailsService;

    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String login() {
        return "login";
    }


    @PostMapping("/login")
    public String authenticate(@RequestParam("userName") String userName,
                               @RequestParam("password") String password,
                               Model model) {

        if ("admin".equals(userName) && "admin".equals(password)) {
            User defaultUser = new User();
            defaultUser.setUserName(userName);
            defaultUser.setPassword(password);
            defaultUser.setStatus("Active");

            model.addAttribute("years", yearDetailsService.getAll());
            model.addAttribute("semesters", semesterDetailsService.getAll());
            return "dashboard";
        }


        User user = userService.findByUserNameAndPassword(userName, password);

        if (user != null && "Active".equalsIgnoreCase(user.getStatus())) {
            model.addAttribute("years", yearDetailsService.getAll());
            model.addAttribute("semesters", semesterDetailsService.getAll());
            return "dashboard";
        } else {
            model.addAttribute("error", "Invalid username or password.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout() {
        return "login";
    }
}
