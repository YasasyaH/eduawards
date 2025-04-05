package com.school.eduawards.repository;

import com.school.eduawards.entity.SemesterHolidays;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SemesterHolidayRepository extends JpaRepository<SemesterHolidays, Integer> {
}
