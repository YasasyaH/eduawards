package com.school.eduawards.controller;

import com.school.eduawards.entity.UserGroup;
import com.school.eduawards.entity.YearDetails;
import com.school.eduawards.service.UserGroupService;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/user-group")
public class UserGroupController {

    @Autowired
    private UserGroupService userGroupService;

    @GetMapping
    public String userGroupDetails(Model model) {
        List<UserGroup> list = userGroupService.getAll();
        model.addAttribute("userGroupList", list);
        model.addAttribute("userGroup", new UserGroup());
        return "user-group-form";
    }

    @PostMapping("/save")
    public String saveUserGroup(@ModelAttribute("userGroup") UserGroup userGroup) {
        if (userGroup.getId() == null) {
            userGroup.setEnterDate(LocalDateTime.now());
            userGroup.setEnterUser("admin");
        }
        userGroupService.save(userGroup);
        return "redirect:/user-group";
    }

    @GetMapping("/edit/{id}")
    public String editUserGroup(@PathVariable("id") Integer id, Model model) {
        UserGroup userGroup = userGroupService.getById(id);
        List<UserGroup> list = userGroupService.getAll();
        model.addAttribute("userGroup", userGroup);
        model.addAttribute("userGroupList", list);

        return "user-group";
    }

    @GetMapping("/delete/{id}")
    public String deleteUserGroup(@PathVariable("id") Integer id) {
        userGroupService.delete(id);
        return "redirect:/user-group";
    }
}