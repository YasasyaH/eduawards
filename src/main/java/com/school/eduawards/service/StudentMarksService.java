package com.school.eduawards.service;

import com.school.eduawards.entity.Student;
import com.school.eduawards.entity.StudentMarks;
import com.school.eduawards.repository.StudentMarksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentMarksService {
    @Autowired
    private StudentMarksRepository marksRepository;

    public Map<String, StudentMarks> getTopStudentsBySubject(int yearId, int semesterId) {
        List<StudentMarks> marksList = marksRepository.findByYearAndSemester(yearId, semesterId);

        for(StudentMarks st: marksList){
            System.out.println("*********" + st.getStudent().getFullName());
        }

        Map<String, StudentMarks> topStudents = new HashMap<>();
        topStudents.put("english", marksList.stream().max(Comparator.comparingInt(StudentMarks::getEnglish)).orElse(null));
        topStudents.put("maths", marksList.stream().max(Comparator.comparingInt(StudentMarks::getMaths)).orElse(null));
        topStudents.put("science", marksList.stream().max(Comparator.comparingInt(StudentMarks::getScience)).orElse(null));
        topStudents.put("computer", marksList.stream().max(Comparator.comparingInt(StudentMarks::getComputer)).orElse(null));
        topStudents.put("socialStudies", marksList.stream().max(Comparator.comparingInt(StudentMarks::getSocialStudies)).orElse(null));
        return topStudents;
    }

    public StudentMarks save(StudentMarks studentMarks) {
        return marksRepository.save(studentMarks);
    }

    public List<StudentMarks> getAll() {
        return marksRepository.findAll();
    }

    public StudentMarks getById(Integer id) {
        return marksRepository.findById(id).orElse(null);
    }

    public void delete(Integer id) {
        marksRepository.deleteById(id);
    }

}
