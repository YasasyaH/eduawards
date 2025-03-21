package com.school.eduawards.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_groups")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_type")
    private String userType;

    private String status;

    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "enter_user")
    private String enterUser;
}