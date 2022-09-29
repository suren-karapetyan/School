package com.example.school.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "gender")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Gender {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name = "identifier")
    protected String identifier;

}
