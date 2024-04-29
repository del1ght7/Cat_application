package com.del1ght7.catApplication.controller;

import com.del1ght7.catApplication.cache.InMemoryMap;
import com.del1ght7.catApplication.model.Cat;
import com.del1ght7.catApplication.service.CatService;
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

class CatControllerTest {

    @Mock
    private CatService catService;

    @Mock
    private InMemoryMap<Long, Cat> singleObjCache;

    @Mock
    private InMemoryMap<Integer, List<Cat>> listObjCache;

    @InjectMocks
    private CatController catController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(catController).build();
    }

    @Test
    void testPostCat() throws Exception {
        Cat cat1 = new Cat();
        cat1.setName("Tom");
        Cat cat2 = new Cat();
        cat2.setName("Jerry");
        List<Cat> cats = Arrays.asList(cat1, cat2);

        when(catService.postCat(cats)).thenReturn(cats);

        mockMvc.perform(post("/api/v1/cats/add/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("[{\"name\":\"Tom\"},{\"name\":\"Jerry\"}]"))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllCats() throws Exception {
        Cat cat1 = new Cat();
        cat1.setName("Tom");
        Cat cat2 = new Cat();
        cat2.setName("Jerry");
        List<Cat> cats = Arrays.asList(cat1, cat2);

        when(catService.getAllCats()).thenReturn(cats);

        mockMvc.perform(get("/api/v1/cats/get/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[1].name").value("Jerry"));

        verify(catService, times(1)).getAllCats();
    }

    @Test
    void testGetAllCatsById() throws Exception {
        Long id = 1L;
        Cat cat = new Cat();
        cat.setName("Tom");

        when(singleObjCache.containsKey(id)).thenReturn(true);
        when(singleObjCache.get(id)).thenReturn(cat);

        mockMvc.perform(get("/api/v1/cats/get/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateCat() throws Exception {
        Cat cat = new Cat();
        cat.setName("Tom");

        when(catService.updateCat(any(Cat.class))).thenReturn(cat);

        mockMvc.perform(put("/api/v1/cats/update/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Tom\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tom"));

        verify(catService, times(1)).updateCat(any(Cat.class));
    }

    @Test
    void testDeleteCat() throws Exception {
        Long id = 1L;

        when(catService.deleteCat(id)).thenReturn(id);

        mockMvc.perform(delete("/api/v1/cats/delete/{id}", id))
                .andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(id)));

        verify(catService, times(1)).deleteCat(id);
    }

    @Test
    void testGetAllCatsWithAge() throws Exception {
        int age = 2;
        Cat cat1 = new Cat();
        cat1.setName("Tom");
        Cat cat2 = new Cat();
        cat2.setName("Jerry");
        List<Cat> cats = Arrays.asList(cat1, cat2);

        when(listObjCache.containsKey(age)).thenReturn(true);
        when(listObjCache.get(age)).thenReturn(cats);

        mockMvc.perform(get("/api/v1/cats/get/withage/{age}", age))
                .andExpect(status().isOk());

    }
}
