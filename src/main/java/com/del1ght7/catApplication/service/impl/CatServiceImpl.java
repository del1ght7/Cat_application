package com.del1ght7.catApplication.service.impl;

import com.del1ght7.catApplication.cache.InMemoryMap;
import com.del1ght7.catApplication.model.Cat;
import com.del1ght7.catApplication.repository.CatRepository;
import com.del1ght7.catApplication.service.CatService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CatServiceImpl implements CatService {
    private final CatRepository catRepository;

    public CatServiceImpl(CatRepository catRepository, InMemoryMap cache) {
        this.catRepository = catRepository;
    }

    @Override
    public List<Cat> postCat(List<Cat> cats) {
        return cats.stream()
                .map(catRepository::save)
                .filter(breed -> breed != null)
                .toList();
    }

    @Override
    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    @Override
    public List<Cat> getAllCatsByAge(int age) {
        if (catRepository.findCatByAge(age) == null) {
            throw new IllegalArgumentException("error 500 (not found in DB)");
        }
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
        if (catRepository.findCatById(id) == null) {
            throw new IllegalArgumentException("error 500 (not found in DB)");
        }
        return catRepository.findCatById(id);
    }
}
