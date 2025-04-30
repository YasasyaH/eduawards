package com.school.eduawards.service;

import com.school.eduawards.entity.Student;
import com.school.eduawards.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;

    public List<Student> getAll() {
        return repository.findAll();
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public Student getById(Integer id) {
        return repository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        repository.deleteById(id);
    }

    public List<Student> findByYearAndClass(int year, String className) {
        return repository.findByCurrentYearAndCurrentClass(year, className);
    }

    public List<Student> searchStudentsByName(String name) {
        return repository.findByFullNameContainingIgnoreCase(name);
    }
}
