package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.repository.IPersonalInfoRepository;
import org.springframework.stereotype.Service;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoService implements  IPersonalInfoService {

    private final IPersonalInfoRepository personalInfoRepository;
    private final Validator validator ;
    public PersonalInfoService(IPersonalInfoRepository personalInfoRepository, Validator validator) {
         this.validator = validator;
        this.personalInfoRepository = personalInfoRepository;
    }

    @Override
    @Transactional
    public PersonalInfo save(PersonalInfo personalInfo) {
        BindingResult result = new BeanPropertyBindingResult(personalInfo, "personalInfo");
        validator.validate(personalInfo,result);

        if (result.hasErrors()){
            System.out.println("Validation errors: " + result.getAllErrors());
            throw new ValidationException(result);
        }
        return personalInfoRepository.save(personalInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
