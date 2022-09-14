package com.example.school.domain;

import com.example.school.dto.Subject;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.persistence.*;
import java.lang.reflect.Type;
import java.util.List;

@Entity
@Table(name="student")
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }

    public List<Subject> getSubjectList() {
        Type requiredType = new TypeToken<List<Subject>>(){}.getType();
        subjectList = new Gson().fromJson(subjects, requiredType);
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
        this.subjects = new Gson().toJson(subjectList);
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subjects='" + subjects + '\'' +
                ", subjectList=" + subjectList +
                '}';
    }
}
