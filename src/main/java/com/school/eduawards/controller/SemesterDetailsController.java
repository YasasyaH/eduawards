package com.school.eduawards.controller;

import com.school.eduawards.entity.SemesterDetails;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/semester-details")
public class SemesterDetailsController {

    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @Autowired
    private YearDetailsService yearDetailsService;
    @GetMapping("/edit/{id}")
    public String editSemesterDetails(@PathVariable Integer id, Model model) {
        SemesterDetails semesterDetails = semesterDetailsService.findById(id);
        model.addAttribute("semesterDetails", semesterDetails);
        model.addAttribute("years", yearDetailsService.getAll()); // Fetch list of years for the dropdown
        return "semester-details-form";
    }

    @PostMapping("/save")
    public String saveSemesterDetails(@ModelAttribute  SemesterDetails semesterDetails) {
        semesterDetailsService.saveOrUpdate(semesterDetails);
        return "redirect:/semester-details"; // Redirect to list after saving
    }

    @GetMapping("/delete/{id}")
    public String deleteSemesterDetails(@PathVariable Integer id) {
        semesterDetailsService.delete(id);
        return "redirect:/semester-details"; // Redirect to list after deletion
    }

    @GetMapping
    public String listSemesterDetails(Model model) {
        model.addAttribute("semesterDetailsList", semesterDetailsService.getAll());
        model.addAttribute("years", yearDetailsService.getAll()); // Fetch list of years for the dropdown
        return "semester-details-form"; // Return the same view with data
    }
}