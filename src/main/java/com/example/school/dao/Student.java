package com.example.school.dao;

import com.example.school.dto.Subject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.*;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

@Entity
@Table(name="student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Student {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="subjects")
    private String subjects;

    @Transient
    private List<Subject> subjectList;

    public List<Subject> getSubjectList() {
        Type requiredType = new TypeToken<List<Subject>>(){}.getType();
        subjectList = new Gson().fromJson(subjects, requiredType);
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
        this.subjects = new Gson().toJson(subjectList);
    }
}
