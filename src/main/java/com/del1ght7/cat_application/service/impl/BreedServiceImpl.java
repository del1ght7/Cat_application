package com.del1ght7.cat_application.service.impl;

import com.del1ght7.cat_application.model.Breed;
import com.del1ght7.cat_application.model.Cat;
import com.del1ght7.cat_application.repository.BreedRepository;
import com.del1ght7.cat_application.service.BreedService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;
    public BreedServiceImpl(BreedRepository breedRepository){
        this.breedRepository = breedRepository;
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
    public void deleteBreed(Breed breed) {
        if (breed!= null){
            if (breed.getCats() != null) {
                List<Cat> cats = breed.getCats().stream().toList();
                for(Cat cat:cats){
                    cat.setBreed(null);
                }
            }
            breedRepository.delete(breed);
        }
    }
}
