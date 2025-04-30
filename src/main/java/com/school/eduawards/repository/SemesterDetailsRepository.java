package com.school.eduawards.repository;


import com.school.eduawards.entity.SemesterDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SemesterDetailsRepository extends JpaRepository<SemesterDetails, Integer> {
    Optional<SemesterDetails> findByIsCurrentTrue();

}