package com.del1ght7.catApplication.service;

import com.del1ght7.catApplication.model.Cat;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CatService {
    List<Cat> postCat(List<Cat> cat);

    List<Cat> getAllCats();

    List<Cat> getAllCatsByAge(int age);

    Cat updateCat(Cat cat);

    Long deleteCat(Long id);

    Cat getCatById(Long id);
}