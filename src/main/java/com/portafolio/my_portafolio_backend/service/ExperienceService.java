package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.repository.IExperienceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ExperienceService implements  IExperienceService {

    private final IExperienceRepository experienceRepository;

    public ExperienceService(IExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }
    @Override
    public Experience save(Experience experience) {
        return experienceRepository.save(experience);
    }

    @Override
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        experienceRepository.deleteById(id);
    }
}
