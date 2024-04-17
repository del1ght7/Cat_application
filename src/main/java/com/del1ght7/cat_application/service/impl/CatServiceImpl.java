package com.del1ght7.cat_application.service.impl;

import com.del1ght7.cat_application.cache.InMemoryMap;
import com.del1ght7.cat_application.model.Cat;
import com.del1ght7.cat_application.repository.CatRepository;
import com.del1ght7.cat_application.service.CatService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    public CatServiceImpl(CatRepository catRepository, InMemoryMap cache) {
        this.catRepository = catRepository;
    }

    @Override
    public Cat postCat(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @Override
    public List<Cat> getAllCatsByAge(int age) {
        return catRepository.findCatByAge(age);
    }

    @Override
    public Cat updateCat(Cat cat) {
        return catRepository.save(cat);
    }

    @Override
    public Long deleteCat(Long id) {
        catRepository.deleteById(id);
        return id;
    }

    @Override
    public Cat getCatById(Long id) {
        return catRepository.findCatById(id);
    }
}
