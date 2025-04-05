package com.school.eduawards.controller;

import com.school.eduawards.entity.User;
import com.school.eduawards.entity.UserGroup;
import com.school.eduawards.service.UserGroupService;
import com.school.eduawards.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping
    public String userDetails(Model model) {
        List<User> list = userService.getAll();
        List<UserGroup> userGroupList = userGroupService.getAll();
        model.addAttribute("userList", list);
        model.addAttribute("3", userGroupList);
        model.addAttribute("user", new User());
        return "user-form";
    }

    @PostMapping("/save")
    public String saveUser(@ModelAttribute("user") User user) {
        if (user.getId() == null) {
            user.setEnterDate(LocalDateTime.now());
            user.setEnterUser("admin");
        }
        userService.save(user);
        return "redirect:/user";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        List<User> list = userService.getAll();
        List<UserGroup> userGroupList = userGroupService.getAll();
        model.addAttribute("userGroupList", userGroupList);
        model.addAttribute("user", user);
        model.addAttribute("userList", list);

        return "user";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userService.delete(id);
        return "redirect:/user";
    }

}
