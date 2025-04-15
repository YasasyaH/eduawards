package com.school.eduawards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "student")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;
    private String nameWithInitials;
    private String registrationNo;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private List<StudentAddress> studentAddresses = new ArrayList<>();

    private String fatherName;
    private String fatherContact;
    private String motherName;
    private String motherContact;
    private String homeContact;
    private String status;
    private int currentYear;
    private String currentClass;

    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "enter_user")
    private String enterUser;
}
