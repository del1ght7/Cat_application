package com.del1ght7.cat_application.controller;

import com.del1ght7.cat_application.model.Breed;
import com.del1ght7.cat_application.service.BreedService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/breeds")
public class BreedController {
    private final BreedService service;

    @PostMapping()
    public Breed postBreed(@RequestBody Breed breed) {
        return service.postBreed(breed);
    }

    @GetMapping()
    public List<Breed> getAllBreeds() {
        return service.getAllBreed();
    }

    @GetMapping("/cats")
    public List<Breed> getBreedsByCatAgeGreaterThan(@RequestParam int age) {
        return service.getBreedsByCatAgeGreaterThan(age);
    }

    @PutMapping()
    public Breed updateBreed(@RequestBody Breed breed) {
        return service.updateBreed(breed);
    }

    @DeleteMapping("{id}")
    public void deleteBreed(@PathVariable Long id) {
        service.deleteBreed(id);
    }
}