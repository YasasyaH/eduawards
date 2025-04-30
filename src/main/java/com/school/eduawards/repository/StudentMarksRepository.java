package com.school.eduawards.repository;

import com.school.eduawards.entity.StudentMarks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentMarksRepository extends JpaRepository<StudentMarks, Integer> {

    @Query("SELECT m FROM StudentMarks m WHERE m.yearDetails.id = :yearId AND m.semesterDetails.id = :semesterId")
    List<StudentMarks> findByYearAndSemester(int yearId, int semesterId);

}
