package com.del1ght7.cat_application.service;

import com.del1ght7.cat_application.model.Cat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {
    Cat postCat(Cat cat);

    List<Cat> getAllCats();

    List<Cat> getAllCatsByAge(int age);

    Cat updateCat(Cat cat);

    Long deleteCat(Long id);

    Cat getCatById(Long id);
}