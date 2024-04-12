package com.del1ght7.cat_application.service;

import com.del1ght7.cat_application.model.Breed;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BreedService {
    List<Breed> getBreedsByCatAgeGreaterThan(int age);
    Breed postBreed(Breed breed);
    List<Breed> getAllBreed();
    Breed updateBreed(Breed breed);
    Breed deleteBreed(Long id);
}
