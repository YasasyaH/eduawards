package com.school.eduawards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "semister_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "year_id", nullable = false)
    private YearDetails yearDetails;

    private LocalDate startDate;
    private LocalDate endDate;
    private String description;
    private String status;

    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "enter_user")
    private String enterUser;
}
