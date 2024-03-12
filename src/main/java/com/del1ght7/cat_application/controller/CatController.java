package com.del1ght7.cat_application.controller;

import com.del1ght7.cat_application.model.Cat;
import com.del1ght7.cat_application.service.CatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/cats")
public class CatController {
    private final CatService service;
    @PostMapping("post")
    public Cat postCat(@RequestBody Cat cat) {
        return service.postCat(cat);
    }
    @GetMapping()
    public List<Cat> getAllCats() {
        return service.getAllCats();
    }
    @GetMapping("random")
    public Cat getRandomCat() {
        return service.getRandomCat();
    }

    @PutMapping("update-cat")
    public Cat updateFactByCountry(@RequestBody Cat cat) {
        return service.updateCatByCountry(cat);
    }
    @DeleteMapping("delete-cat")
    public void deleteCat(@RequestBody Cat cat) {
        service.deleteCat(cat);
    }

}
