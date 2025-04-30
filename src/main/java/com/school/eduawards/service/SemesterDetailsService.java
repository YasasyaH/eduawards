package com.school.eduawards.service;

import com.school.eduawards.entity.SemesterDetails;
import com.school.eduawards.repository.SemesterDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SemesterDetailsService {

    private final SemesterDetailsRepository semesterDetailsRepository;

    @Autowired
    public SemesterDetailsService(SemesterDetailsRepository semesterDetailsRepository) {
        this.semesterDetailsRepository = semesterDetailsRepository;
    }

    // Save or update semester details
    public SemesterDetails saveOrUpdate(SemesterDetails semesterDetails) {
        return semesterDetailsRepository.save(semesterDetails);
    }

    // Get all semester details
    public List<SemesterDetails> getAll() {
        return semesterDetailsRepository.findAll();
    }

    // Find a semester details by its ID
    public SemesterDetails findById(Integer id) {
        Optional<SemesterDetails> semesterDetails = semesterDetailsRepository.findById(id);
        return semesterDetails.orElse(null);
    }

    // Delete semester details by ID
    public void delete(Integer id) {
        semesterDetailsRepository.deleteById(id);
    }

    public SemesterDetails findCurrentSemester() {
        return semesterDetailsRepository.findByIsCurrentTrue()
                .orElseThrow(() -> new RuntimeException("Current semester not found"));
    }

}