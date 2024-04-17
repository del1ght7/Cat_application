package com.del1ght7.cat_application.service.impl;

import com.del1ght7.cat_application.cache.InMemoryMap;
import com.del1ght7.cat_application.model.Breed;
import com.del1ght7.cat_application.model.Cat;
import com.del1ght7.cat_application.repository.BreedRepository;
import com.del1ght7.cat_application.service.BreedService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
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
    public List<Breed> getBreedsByCatAgeGreaterThan(int age) {
        String cacheKey = "breedsByCatAge_" + age;
        List<Breed> breeds = (List<Breed>) cache.get(cacheKey);
        if (breeds == null) {
            breeds = breedRepository.findBreedsByCatAgeGreaterThan(age);
            cache.put(cacheKey, breeds);
        }
        return breeds;
    }

    @Override
    public Breed postBreed(Breed breed) {
        Breed savedBreed = breedRepository.save(breed);
        cache.put("breed_" + savedBreed.getId(), savedBreed);
        return savedBreed;
    }

    @Override
    public Breed updateBreed(Breed breed) {
        Breed updatedBreed = breedRepository.save(breed);
        cache.put("breed_" + updatedBreed.getId(), updatedBreed);
        return updatedBreed;
    }
    @Override
    public List<Breed> getAllBreed() {
        return breedRepository.findAll();
    }



    @Override
    public Breed deleteBreed(Long id) {
        Breed breed = breedRepository.findBreedById(id);
        if (breed != null) {
            if (breed.getCats() != null) {
                List<Cat> cats = new ArrayList<>(breed.getCats());
                for (Cat cat : cats) {
                    cat.setBreed(null);
                }
            }
            breedRepository.delete(breed);
            cache.remove("breed_" + breed.getId()); // Удаление из кэша
        }
        return null;
    }
}