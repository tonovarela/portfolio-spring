package com.portafolio.my_portafolio_backend.service.implementation;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.Education;
import com.portafolio.my_portafolio_backend.repository.interfaces.IEducationRepository;
import com.portafolio.my_portafolio_backend.service.interfaces.IEducationService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;
import org.springframework.stereotype.Service;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.util.List;
import java.util.Optional;
@Service
public class EducationService implements IEducationService {

    private final IEducationRepository educationRepository;
    private final Validator validator;

    public EducationService(IEducationRepository educationRepository, Validator validator) {
        this.educationRepository = educationRepository;
        this.validator = validator;
    }

    @Override
    @Transactional
    public Education save(Education education) {
        BindingResult result = new BeanPropertyBindingResult(education, "education");
        validator.validate(education, result);
        if (result.hasErrors()) {
            throw new ValidationException(result);
        }
        return educationRepository.save(education);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }
}
