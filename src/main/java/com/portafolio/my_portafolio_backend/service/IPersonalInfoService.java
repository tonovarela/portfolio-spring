package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;


import java.util.List;
import java.util.Optional;

public interface IPersonalInfoService {



    PersonalInfo save(PersonalInfo personalInfo);
    Optional<PersonalInfo> findById(Long id);
    List<PersonalInfo> findAll();
    void deleteById(Long id);


}
