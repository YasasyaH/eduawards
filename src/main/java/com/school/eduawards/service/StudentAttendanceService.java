package com.school.eduawards.service;

import com.school.eduawards.entity.StudentAttendance;
import com.school.eduawards.repository.StudentAttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentAttendanceService {
    @Autowired
    private StudentAttendanceRepository studentAttendanceRepository;

    public StudentAttendance save(StudentAttendance attendance) {
        return studentAttendanceRepository.save(attendance);
    }


}
