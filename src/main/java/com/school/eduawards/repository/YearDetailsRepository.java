package com.school.eduawards.repository;

import com.school.eduawards.entity.YearDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YearDetailsRepository extends JpaRepository<YearDetails, Integer> {
}