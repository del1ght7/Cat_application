package com.del1ght7.cat_application.Service.impl;

import com.del1ght7.cat_application.model.Cat;
import com.del1ght7.cat_application.repository.InMemoryCatDAO;
import com.del1ght7.cat_application.Service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class InMemoryCatServiceImpl implements CatService {
    private final InMemoryCatDAO repository;

    @Override
    public Cat postCat(Cat cat) {
        return repository.postCat(cat);
    }

    @Override
    public List<Cat> getAllCats() {
        return repository.getAllCat();
    }

    @Override
    public Cat getRandomCat() {
        return repository.getRandomCat();
    }

    @Override
    public Cat updateCatByCountry(Cat cat) {
        return repository.updateCatByCountry(cat);
    }

    @Override
    public void deleteCat(Cat cat) {
        repository.deleteCat(cat);
    }
}