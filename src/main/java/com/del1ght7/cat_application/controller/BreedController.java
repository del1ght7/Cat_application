package com.del1ght7.cat_application.controller;


import com.del1ght7.cat_application.model.Breed;
import com.del1ght7.cat_application.service.BreedService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/breeds")
public class BreedController {
    private final BreedService service;
    public BreedController(BreedService breedService){
        this.service = breedService;
    }
    @PostMapping()
    public Breed postBreed(@RequestBody Breed breed) {
        return service.postBreed(breed);
    }
    @GetMapping()
    public List<Breed> getAllBreeds() {
        return service.getAllBreed();
    }
    @PutMapping()
    public Breed updateBreed(@RequestBody Breed breed) {
        return service.updateBreed(breed);
    }
    @DeleteMapping()
    public void deleteBreed(@RequestBody Breed breed) {
        service.deleteBreed(breed);
    }
}
