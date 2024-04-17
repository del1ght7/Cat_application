package com.del1ght7.cat_application.service.impl;

import com.del1ght7.cat_application.cache.InMemoryMap;
import com.del1ght7.cat_application.model.Breed;
import com.del1ght7.cat_application.model.Cat;
import com.del1ght7.cat_application.repository.BreedRepository;
import com.del1ght7.cat_application.service.BreedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;
    private final InMemoryMap cache;

    public BreedServiceImpl(BreedRepository breedRepository, InMemoryMap cache) {
        this.breedRepository = breedRepository;
        this.cache = cache;
    }

    @Override
    public Breed postBreed(Breed breed) {
        return breedRepository.save(breed);
    }

    @Override
    public List<Breed> getAllBreed() {
        return breedRepository.findAll();
    }

    @Override
    public Breed updateBreed(Breed breed) {
        return breedRepository.save(breed);
    }

    @Override
    public Breed getBreedById(Long id) {
        return breedRepository.findBreedById(id);
    }

    @Override
    public Long deleteBreed(Long id) {
        Breed breed = breedRepository.findBreedById(id);
        if (breed != null) {
            if (breed.getCats() != null) {
                List<Cat> cats = breed.getCats().stream().toList();
                for (Cat cat : cats) {
                    cat.setBreed(null);
                }
            }
            breedRepository.delete(breed);
        }
        return id;
    }
}