package com.del1ght7.catApplication.service;

import com.del1ght7.catApplication.model.Breed;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BreedService {
    List<Breed> postBreed(List<Breed> breed);

    List<Breed> getAllBreed();

    Breed getBreedById(Long id);

    Breed updateBreed(Breed breed);

    Long deleteBreed(Long id);
}
