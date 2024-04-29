package com.del1ght7.catApplication.service.impl;

import com.del1ght7.catApplication.cache.InMemoryMap;
import com.del1ght7.catApplication.model.Cat;
import com.del1ght7.catApplication.repository.CatRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CatServiceImplTest {

    @Mock
    private CatRepository catRepository;

    @Mock
    private InMemoryMap cache;

    @InjectMocks
    private CatServiceImpl catService;

    public CatServiceImplTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPostCat() {
        Cat cat1 = new Cat();
        cat1.setName("Tom");
        Cat cat2 = new Cat();
        cat2.setName("Jerry");
        List<Cat> cats = new ArrayList<>();
        cats.add(cat1);
        cats.add(cat2);

        when(catRepository.save(any(Cat.class))).thenReturn(cat1, cat2);

        List<Cat> savedCats = catService.postCat(cats);

        assertEquals(2, savedCats.size());
        assertEquals("Tom", savedCats.get(0).getName());
        assertEquals("Jerry", savedCats.get(1).getName());
    }

    @Test
    void testGetAllCats() {
        List<Cat> expectedCats = new ArrayList<>();
        expectedCats.add(new Cat("Tom"));
        expectedCats.add(new Cat("Jerry"));

        when(catRepository.findAll()).thenReturn(expectedCats);

        List<Cat> actualCats = catService.getAllCats();

        assertEquals(expectedCats, actualCats);
    }

    @Test
    void testGetAllCatsByAge() {
        int age = 2;
        List<Cat> expectedCats = new ArrayList<>();
        expectedCats.add(new Cat("Tom", age));
        expectedCats.add(new Cat("Jerry", age));

        when(catRepository.findCatByAge(age)).thenReturn(expectedCats);

        List<Cat> actualCats = catService.getAllCatsByAge(age);

        assertEquals(expectedCats, actualCats);
    }

    @Test
    void testUpdateCat() {
        Cat cat = new Cat("Tom");
        cat.setId(1L);

        when(catRepository.save(cat)).thenReturn(cat);

        Cat updatedCat = catService.updateCat(cat);

        assertEquals(cat, updatedCat);
    }

    @Test
    void testDeleteCat() {
        Long id = 1L;

        Long deletedId = catService.deleteCat(id);

        assertEquals(id, deletedId);
        verify(catRepository, times(1)).deleteById(id);
    }

    @Test
    void testGetCatById() {
        Long id = 1L;
        Cat cat = new Cat("Tom");
        cat.setId(id);

        when(catRepository.findCatById(id)).thenReturn(cat);

        Cat retrievedCat = catService.getCatById(id);

        assertEquals(cat, retrievedCat);
    }

    @Test
    void testGetCatById_NotFound() {
        Long id = 1L;

        when(catRepository.findCatById(id)).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> catService.getCatById(id));
    }
}
