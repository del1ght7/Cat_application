package com.del1ght7.cat_application.repository;

import com.del1ght7.cat_application.model.Cat;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Repository
public class InMemoryCatDAO {
    private final List<Cat> catList = new ArrayList<>();
    private Random rand = new Random();
    public Cat postCat(Cat cat) {
        catList.add(cat);
        return cat;
    }
    public List<Cat> getAllCat() {
        return catList;
    }
    public Cat getRandomCat() {
        return catList.get(rand.nextInt(0,catList.size()));
    }
    public Cat updateCatByCountry(Cat cat) {
        var catIndex = IntStream.range(0, catList.size() -1)
                .filter(index -> catList.get(index).getCountry().equals(cat.getCountry()))
                .findFirst()
                .orElse(-1);
        if (catIndex > -1) {
            catList.set(catIndex, cat);
            return cat;
        }
        return null;
    }
    public void deleteCat(Cat Cat) {
        catList.remove(Cat);
    }
}