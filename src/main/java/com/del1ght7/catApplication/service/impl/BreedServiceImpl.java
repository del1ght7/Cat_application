package com.del1ght7.catApplication.service.impl;
import com.del1ght7.catApplication.cache.InMemoryMap;
import com.del1ght7.catApplication.model.Breed;
import com.del1ght7.catApplication.repository.BreedRepository;
import com.del1ght7.catApplication.service.BreedService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BreedServiceImpl implements BreedService {
    private final BreedRepository breedRepository;
    private final InMemoryMap cache;

    public BreedServiceImpl(BreedRepository breedRepository, InMemoryMap cache) {
        this.breedRepository = breedRepository;
        this.cache = cache;
    }

    @Override
    public List<Breed> postBreed(List<Breed> breeds) {
        return breeds.stream()
                .map(breedRepository::save)
                .toList();
    }

    @Override
    public List<Breed> getAllBreed() {
        return breedRepository.findAll();
    }

    @Override
    public Breed updateBreed(Breed breed) {
        return breedRepository.save(breed);
    }

    @Override
    public Breed getBreedById(Long id) {
        if (breedRepository.findBreedById(id) == null) {
            throw new IllegalArgumentException("error 500 (not found in DB)");
        }
        return breedRepository.findBreedById(id);
    }

    @Override
    public Long deleteBreed(Long id) {
        Breed breed = breedRepository.findBreedById(id);
        if (breed != null) {
            if (breed.getCats() != null) {
                breed.getCats().forEach(cat -> cat.setBreed(null));
                breed.getCats().clear();
            }
            breedRepository.delete(breed);
        }
        return id;
    }
}