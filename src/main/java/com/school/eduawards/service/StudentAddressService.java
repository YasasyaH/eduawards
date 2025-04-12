package com.school.eduawards.service;

import com.school.eduawards.entity.StudentAddress;
import com.school.eduawards.repository.StudentAddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAddressService {
    @Autowired
    private StudentAddressRepository repository;

    public List<StudentAddress> getAll() {
        return repository.findAll();
    }

    public StudentAddress save(StudentAddress studentAddress) {
        return repository.save(studentAddress);
    }

    public StudentAddress getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
