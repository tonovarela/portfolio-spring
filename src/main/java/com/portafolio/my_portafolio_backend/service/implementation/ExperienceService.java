package com.portafolio.my_portafolio_backend.service.implementation;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.repository.interfaces.IExperienceRepository;
import com.portafolio.my_portafolio_backend.service.interfaces.IExperienceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;
@Service
public class ExperienceService implements IExperienceService {

    private final IExperienceRepository experienceRepository;
    private final Validator validator;

    public ExperienceService(IExperienceRepository experienceRepository, Validator validator) {
        this.experienceRepository = experienceRepository;
        this.validator = validator;
    }
    @Override
    @Transactional
    public Experience save(Experience experience) {
        BindingResult result = new BeanPropertyBindingResult(experience, "experience");
        validator.validate(experience, result);
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        return experienceRepository.save(experience);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findById(Long id) {
        return experienceRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return experienceRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        System.out.println("Deleting experience with id: " + id);
        experienceRepository.deleteById(id);
    }
}
