package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Education;
import com.portafolio.my_portafolio_backend.repository.IEducationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class EducationService implements  IEducationService{

    private final IEducationRepository educationRepository;

    public EducationService(IEducationRepository educationRepository) {
        this.educationRepository = educationRepository;
    }

    @Override
    public Education save(Education education) {
        if (education.getStartDate() ==null){
            throw new IllegalArgumentException("La fecha de inicio no puede ser nula");
        }
        if (education.getEndDate()!=null && education.getStartDate().isAfter(education.getEndDate())){
            throw new IllegalArgumentException("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        return educationRepository.save(education);
    }

    @Override
    public Optional<Education> findById(Long id) {
        return educationRepository.findById(id);
    }

    @Override
    public List<Education> findAll() {
        return educationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        educationRepository.deleteById(id);
    }
}
