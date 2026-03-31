package com.portafolio.my_portafolio_backend.repository.interfaces;


import com.portafolio.my_portafolio_backend.model.Experience;
import java.util.List;
import java.util.Optional;

public interface IExperienceRepository {
    Experience save(Experience experience);
    Optional<Experience> findById(Long id);
    List<Experience> findAll();
    void deleteById(Long id);

}
