package com.del1ght7.cat_application.service;

import com.del1ght7.cat_application.model.Breed;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface BreedService {
    Breed postBreed(Breed breed);
    List<Breed> getAllBreed();
    Breed updateBreed(Breed breed);
    void deleteBreed(Breed breed);

}
