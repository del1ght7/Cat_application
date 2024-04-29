package com.del1ght7.catApplication.controller;

import com.del1ght7.catApplication.cache.InMemoryMap;
import com.del1ght7.catApplication.model.Cat;
import com.del1ght7.catApplication.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cats")
public class CatController {
    private final CatService catService;
    private final InMemoryMap<Long, Cat> singleObjCache;
    private final InMemoryMap<Integer, List<Cat>> listObjCache;

    @PostMapping("/add/")
    public ResponseEntity<List<Cat>> postCat(@RequestBody List<Cat> cat) {

        return ResponseEntity.ok(catService.postCat(cat));
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<Cat>> getAllСats() {
        return ResponseEntity.ok(catService.getAllCats());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Cat> getAllCats(@PathVariable Long id) {
        if (singleObjCache.containsKey(id)) {
            return ResponseEntity.ok(singleObjCache.get(id));
        }
        singleObjCache.put(id, catService.getCatById(id));
        return ResponseEntity.ok(singleObjCache.get(id));
    }

    @PutMapping("/update/")
    public ResponseEntity<Cat> updateCat(@RequestBody Cat cat) {
        return ResponseEntity.ok(catService.updateCat(cat));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Long> deleteCat(@PathVariable Long id) {
        return ResponseEntity.ok(catService.deleteCat(id));
    }

    @GetMapping("/get/withage/{age}")
    public ResponseEntity<List<Cat>> getAllCatsWithAge(@PathVariable int age) {
        if (listObjCache.containsKey(age)) {
            return ResponseEntity.ok(listObjCache.get(age));
        }
        listObjCache.put(age, catService.getAllCatsByAge(age));
        return ResponseEntity.ok(listObjCache.get(age));
    }
}
