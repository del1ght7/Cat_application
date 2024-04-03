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
    @PostMapping()
    public Cat postCat(@RequestBody Cat cat) {
        return service.postCat(cat);
    }
    @GetMapping()
    public List<Cat> getAllCats() {
        return service.getAllCats();
    }
    @PutMapping()
    public Cat updateCat(@RequestBody Cat cat) {
        return service.updateCat(cat);
    }
    @DeleteMapping()
    public void deleteCat(@RequestBody Cat cat) {
        service.deleteCat(cat);
    }
}
