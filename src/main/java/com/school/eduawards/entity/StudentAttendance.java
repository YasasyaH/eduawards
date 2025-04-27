package com.school.eduawards.entity;

import com.school.eduawards.dto.AttendanceRecord;
import com.vladmihalcea.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

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
    @JoinColumn(name = "semister_id", nullable = false)
    private SemesterDetails semesterDetails;

    private int month;

    private String type;

    private String status;

    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private AttendanceRecord attendanceRecord;


    @Column(name = "enter_date")
    private LocalDateTime enterDate;

    @Column(name = "enter_user")
    private String enterUser;
}
