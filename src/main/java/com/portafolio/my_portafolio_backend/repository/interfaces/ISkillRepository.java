package com.portafolio.my_portafolio_backend.repository.interfaces;

import com.portafolio.my_portafolio_backend.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillRepository {

    Skill save(Skill skill);
    Optional<Skill> findById(Long id);
    List<Skill> findAll();
    void deleteById(Long id);

}
