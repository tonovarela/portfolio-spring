package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.repository.IPersonalInfoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonalInfoService implements  IPersonalInfoService {

    private final IPersonalInfoRepository personalInfoRepository;
    public PersonalInfoService(IPersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }
    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        return personalInfoRepository.save(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> findById(Long id) {
        return personalInfoRepository.findById(id);
    }

    @Override
    public List<PersonalInfo> findAll() {
        return personalInfoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        personalInfoRepository.deleteById(id);
    }
}
