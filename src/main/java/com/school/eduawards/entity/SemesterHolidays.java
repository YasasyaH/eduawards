package com.school.eduawards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "semister_holidays")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterHolidays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "year_id", nullable = false)
    private YearDetails yearDetails;

    @ManyToOne
    @JoinColumn(name = "semister_id", nullable = false)
    private SemesterDetails semesterDetails;

    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String status;

    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "enter_user")
    private String enterUser;
}
