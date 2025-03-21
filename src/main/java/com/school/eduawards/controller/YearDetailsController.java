package com.school.eduawards.controller;

import com.school.eduawards.entity.YearDetails;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/year-details")
public class YearDetailsController {

    @Autowired
    private YearDetailsService service;

    @GetMapping
    public String listYearDetails(Model model) {
        List<YearDetails> list = service.getAll();
        model.addAttribute("yearDetailsList", list);
        model.addAttribute("yearDetails", new YearDetails());
        return "year-details";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("yearDetails") YearDetails yearDetails) {
        if (yearDetails.getId() == null) {
            yearDetails.setEnterDate(LocalDateTime.now());
            yearDetails.setEnterUser("admin"); // Replace with logged-in user if applicable
        }
        service.save(yearDetails);
        return "redirect:/year-details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        YearDetails yd = service.getById(id);
        List<YearDetails> list = service.getAll();
        model.addAttribute("yearDetails", yd);
        model.addAttribute("yearDetailsList", list);
        return "year-details";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        service.delete(id);
        return "redirect:/year-details";
    }
}