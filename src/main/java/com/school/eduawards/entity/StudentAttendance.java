package com.school.eduawards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_attendance")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentAttendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "year_id", nullable = false)
    private YearDetails yearDetails;

    @ManyToOne
    @JoinColumn(name = "semester_id", nullable = false)
    private SemesterDetails semesterDetails;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    private int month;

    private String type;

    private String status;

    private boolean present;

    @Column(name = "attendance_date")
    private LocalDateTime attendanceDate;

    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "enter_user")
    private String enterUser;
}
