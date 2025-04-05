package com.school.eduawards.service;

import com.school.eduawards.entity.SemesterHolidays;
import com.school.eduawards.repository.SemesterHolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterHolidayService {
    private final SemesterHolidayRepository semesterHolidayRepository;

    @Autowired
    public SemesterHolidayService(SemesterHolidayRepository semesterHolidayRepository) {
        this.semesterHolidayRepository = semesterHolidayRepository;
    }

    public SemesterHolidays saveOrUpdate(SemesterHolidays semesterHolidays) {
        return semesterHolidayRepository.save(semesterHolidays);
    }

    public List<SemesterHolidays> getAll() {
        return semesterHolidayRepository.findAll();
    }

    public SemesterHolidays findById(Integer id) {
        Optional<SemesterHolidays> semesterDetails = semesterHolidayRepository.findById(id);
        return semesterDetails.orElse(null);
    }

    public void delete(Integer id) {
        semesterHolidayRepository.deleteById(id);
    }
}
