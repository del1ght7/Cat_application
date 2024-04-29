package com.del1ght7.catApplication.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.del1ght7.catApplication.cache.InMemoryMap;
import com.del1ght7.catApplication.model.Breed;
import com.del1ght7.catApplication.repository.BreedRepository;

class BreedServiceImplTest {

    @Mock
    private BreedRepository breedRepository;

    @Mock
    private InMemoryMap cache;

    @InjectMocks
    private BreedServiceImpl breedService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testPostBreed() {

        Breed breed1 = createBreed("Siamese", "Thailand");
        Breed breed2 = createBreed("Persian", "Iran");
        List<Breed> breeds = Arrays.asList(breed1, breed2);

        when(breedRepository.save(any(Breed.class))).thenReturn(breed1, breed2);

        List<Breed> savedBreeds = breedService.postBreed(breeds);

        assertEquals(2, savedBreeds.size());
        assertEquals("Siamese", savedBreeds.get(0).getName());
        assertEquals("Persian", savedBreeds.get(1).getName());
    }

    @Test
    void testGetAllBreed() {
        List<Breed> breeds = Arrays.asList(
                createBreed("Siamese", "Thailand"),
                createBreed("Persian", "Iran")
        );

        when(breedRepository.findAll()).thenReturn(breeds);

        List<Breed> allBreeds = breedService.getAllBreed();

        assertEquals(2, allBreeds.size());
        assertEquals("Siamese", allBreeds.get(0).getName());
        assertEquals("Persian", allBreeds.get(1).getName());
    }

    @Test
    void testUpdateBreed() {
        Breed breed = createBreed("Siamese", "Thailand");

        when(breedRepository.save(any(Breed.class))).thenReturn(breed);

        Breed updatedBreed = breedService.updateBreed(breed);

        assertEquals("Siamese", updatedBreed.getName());
    }

    @Test
    void testGetBreedById() {
        Breed breed = createBreed("Siamese", "Thailand");

        when(breedRepository.findBreedById(anyLong())).thenReturn(breed);

        assertEquals("Siamese", breedService.getBreedById(1L).getName());
    }

    @Test
    void testGetBreedById_NotFound() {
        when(breedRepository.findBreedById(anyLong())).thenReturn(null);

        assertThrows(IllegalArgumentException.class, () -> breedService.getBreedById(1L));
    }

    @Test
    void testDeleteBreed() {
        Breed breed = createBreed("Siamese", "Thailand");
        breed.setCats(new HashSet<>());

        when(breedRepository.findBreedById(anyLong())).thenReturn(breed);

        Long id = breedService.deleteBreed(1L);

        assertEquals(1L, id);
        verify(breedRepository, times(1)).delete(breed);
    }

    @Test
    void testDeleteBreed_NotFound() {
        when(breedRepository.findBreedById(anyLong())).thenReturn(null);

        assertEquals(1L, breedService.deleteBreed(1L));
    }

    private Breed createBreed(String name, String country) {
        return Breed.builder()
                .name(name)
                .country(country)
                .build();
    }
}

