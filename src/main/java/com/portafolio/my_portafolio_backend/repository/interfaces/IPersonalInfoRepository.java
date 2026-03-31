package com.portafolio.my_portafolio_backend.repository.interfaces;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;

import java.util.List;
import java.util.Optional;

public interface IPersonalInfoRepository {
    PersonalInfo save(PersonalInfo personalInfo);
    Optional<PersonalInfo>  findById(Long id);
    List<PersonalInfo> findAll();
    void deleteById(Long id);

}
