package com.example.school.dao;

import com.example.school.dto.Subject;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name="teacher")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Teacher {

    @Id
    @GeneratedValue()
    private long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;
    //TODO check if it is possible to save enum to db
    @Column(name="subject")
    private Subject subject;
}
