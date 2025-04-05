package com.school.eduawards.controller;

import com.school.eduawards.entity.SemesterDetails;
import com.school.eduawards.entity.SemesterHolidays;
import com.school.eduawards.service.SemesterDetailsService;
import com.school.eduawards.service.SemesterHolidayService;
import com.school.eduawards.service.YearDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/semester-holidays")
public class SemesterHolidayController {
    @Autowired
    private SemesterDetailsService semesterDetailsService;

    @Autowired
    private YearDetailsService yearDetailsService;

    @Autowired
    private SemesterHolidayService semesterHolidayService;

    @GetMapping("/edit/{id}")
    public String editSemesterHolidays(@PathVariable Integer id, Model model) {
        SemesterHolidays semesterHolidays = semesterHolidayService.findById(id);
        model.addAttribute("semesterHoliday", new SemesterHolidays());
        model.addAttribute("years", yearDetailsService.getAll());
        model.addAttribute("semesterDetailsList", semesterDetailsService.getAll());
        return "semester-holidays-form";
    }

    @PostMapping("/save")
    public String saveSemesterHoliday(@ModelAttribute SemesterHolidays semesterHolidays) {
        semesterHolidayService.saveOrUpdate(semesterHolidays);
        return "redirect:/semester-holidays";
    }

    @GetMapping("/delete/{id}")
    public String deleteSemesterHoliday(@PathVariable Integer id) {
        semesterHolidayService.delete(id);
        return "redirect:/semester-holidays";
    }

    @GetMapping
    public String listSemesterHolidays(Model model) {
        model.addAttribute("semesterHoliday", new SemesterHolidays());
        model.addAttribute("semesterHolidayList", semesterHolidayService.getAll());
        model.addAttribute("semesterDetailsList", semesterDetailsService.getAll());
        model.addAttribute("years", yearDetailsService.getAll());
        return "semester-holidays-form";
    }
}
