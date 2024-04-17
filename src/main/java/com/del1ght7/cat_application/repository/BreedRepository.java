package com.del1ght7.cat_application.repository;

import com.del1ght7.cat_application.model.Breed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreedRepository extends JpaRepository<Breed, Long> {
    Breed findBreedById(Long id);
}
