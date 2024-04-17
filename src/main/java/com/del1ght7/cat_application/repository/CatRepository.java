package com.del1ght7.cat_application.repository;

import com.del1ght7.cat_application.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CatRepository extends JpaRepository<Cat, Long> {

    @Query("SELECT c FROM Cat c WHERE c.age = :age")
    List<Cat> findCatByAge(int age);

    Cat findCatById(Long id);
}
