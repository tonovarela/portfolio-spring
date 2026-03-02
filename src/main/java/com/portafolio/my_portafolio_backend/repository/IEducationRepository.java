package com.portafolio.my_portafolio_backend.repository;

import com.portafolio.my_portafolio_backend.model.Education;


import java.util.List;
import java.util.Optional;

public interface IEducationRepository {

    Education save(Education education);
    Optional<Education> findById(Long id);
    List<Education> findAll();
    void deleteById(Long id);

}
