package com.del1ght7.catApplication.controller;

import com.del1ght7.catApplication.cache.InMemoryMap;
import com.del1ght7.catApplication.model.Breed;
import com.del1ght7.catApplication.service.BreedService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class BreedControllerTest {

    @Mock
    private BreedService breedService;

    @Mock
    private InMemoryMap<Long, Breed> cache;

    @InjectMocks
    private BreedController breedController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(breedController).build();
    }

    @Test
    void testPostBreed() throws Exception {
        Breed breedFirst = new Breed();
        breedFirst.setName("Siamese");
        Breed breedSecond = new Breed();
        breedSecond.setName("Persian");
        List<Breed> breeds = Arrays.asList(breedFirst, breedSecond);

        when(breedService.postBreed(breeds)).thenReturn(breeds);

        mockMvc.perform(post("/api/v1/breeds/add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Siamese\"},{\"name\":\"Persian\"}]"))
                .andExpect(status().isOk());

    }


    @Test
    void testGetAllBreeds() throws Exception {
        Breed breed1 = new Breed();
        breed1.setName("Siamese");
        Breed breed2 = new Breed();
        breed2.setName("Persian");
        List<Breed> breeds = Arrays.asList(breed1, breed2);

        when(breedService.getAllBreed()).thenReturn(breeds);

        mockMvc.perform(get("/api/v1/breeds/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Siamese"))
                .andExpect(jsonPath("$[1].name").value("Persian"));

        verify(breedService, times(1)).getAllBreed();
    }

    @Test
    void testGetBreeds() throws Exception {
        Breed breed = new Breed();
        breed.setName("Siamese");
        breed.setId((int) 1);

        when(cache.containsKey(1L)).thenReturn(true);
        when(cache.get(1L)).thenReturn(breed);

        mockMvc.perform(get("/api/v1/breeds/get/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Siamese"));

        verify(cache, times(1)).containsKey(1L);
        verify(cache, times(1)).get(1L);
    }

    @Test
    void testUpdateBreed() throws Exception {
        Breed breed = new Breed();
        breed.setName("Siamese");

        when(breedService.updateBreed(any(Breed.class))).thenReturn(breed);

        mockMvc.perform(put("/api/v1/breeds/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Siamese\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Siamese"));

        verify(breedService, times(1)).updateBreed(any(Breed.class));
    }

    @Test
    void testDeleteBreed() throws Exception {
        Long id = 1L;

        when(breedService.deleteBreed(id)).thenReturn(id);

        mockMvc.perform(delete("/api/v1/breeds/delete/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(id)));

        verify(breedService, times(1)).deleteBreed(id);
    }
}
