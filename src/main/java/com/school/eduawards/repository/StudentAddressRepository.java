package com.school.eduawards.repository;

import com.school.eduawards.entity.StudentAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAddressRepository extends JpaRepository<StudentAddress, Integer> {
}
