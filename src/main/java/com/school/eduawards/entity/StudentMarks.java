package com.school.eduawards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "student_marks")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentMarks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "student_year", nullable = false)
    private YearDetails yearDetails;

    @OneToOne
    @JoinColumn(name = "student_semester", nullable = false)
    private SemesterDetails semesterDetails;

    private int english;
    private int maths;
    private int science;
    private int computer;
    @Column(name = "social_studies")
    private int socialStudies;
    private String sinhala;


    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "enter_user")
    private String enterUser;

}
