package com.del1ght7.catApplication.model;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cats")
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer age;

    @ManyToOne
    @JoinColumn(name = "breed")
    private Breed breed;

    public Cat(String name) {
        this.name = name;
    }

    public Cat(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
