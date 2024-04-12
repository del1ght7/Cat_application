package com.del1ght7.cat_application.repository;

import com.del1ght7.cat_application.model.Cat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatRepository extends JpaRepository<Cat, Long> {
}
