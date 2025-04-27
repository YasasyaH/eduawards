package com.school.eduawards.repository;

import com.school.eduawards.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    List<Student> findByCurrentYearAndCurrentClass(int currentYear, String currentClass);
}
