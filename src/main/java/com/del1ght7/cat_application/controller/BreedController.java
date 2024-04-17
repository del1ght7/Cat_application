package com.del1ght7.cat_application.controller;


import com.del1ght7.cat_application.cache.InMemoryMap;
import com.del1ght7.cat_application.model.Breed;
import com.del1ght7.cat_application.service.BreedService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/breeds")
public class BreedController {
    private final BreedService breedService;
    private final InMemoryMap<Long, Breed> cache;

    public BreedController(BreedService breedService, InMemoryMap cache) {
        this.breedService = breedService;
        this.cache = cache;
    }

    @PostMapping("/add/")
    public ResponseEntity<Breed> postBreed(@RequestBody Breed breed) {
        return ResponseEntity.ok(breedService.postBreed(breed));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Breed>> getAllBreeds() {
        return ResponseEntity.ok(breedService.getAllBreed());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Breed> getBreeds(@PathVariable Long id) {
        if (cache.containsKey(id)) {
            return ResponseEntity.ok(cache.get(id));
        }
        cache.put(id, breedService.getBreedById(id));
        return ResponseEntity.ok(cache.get(id));
    }

    @PutMapping("/update/")
    public ResponseEntity<Breed> updateBreed(@RequestBody Breed breed) {
        return ResponseEntity.ok(breedService.updateBreed(breed));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteBreed(@PathVariable Long id) {
        return ResponseEntity.ok(breedService.deleteBreed(id));
    }
}
