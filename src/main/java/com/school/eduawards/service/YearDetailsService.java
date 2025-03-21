package com.school.eduawards.service;

import com.school.eduawards.entity.YearDetails;
import com.school.eduawards.repository.YearDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YearDetailsService {

    @Autowired
    private YearDetailsRepository repository;

    public List<YearDetails> getAll() {
        return repository.findAll();
    }

    public YearDetails save(YearDetails yearDetails) {
        return repository.save(yearDetails);
    }

    public YearDetails getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}