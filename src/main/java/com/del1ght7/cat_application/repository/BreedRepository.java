package com.del1ght7.cat_application.repository;

import com.del1ght7.cat_application.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BreedRepository extends JpaRepository<Breed, Long> {
    Breed findBreedById(Long id);
    @Query("SELECT b FROM Breed b JOIN b.cats c WHERE c.age > :age")
    List<Breed> findBreedByCatAgeGreaterThan(int age);

    List<Breed> findBreedsByCatAgeGreaterThan(int age);
}
