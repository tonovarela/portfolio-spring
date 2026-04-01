package com.portafolio.my_portafolio_backend.service.interfaces;

import com.portafolio.my_portafolio_backend.model.Skill;

import java.util.List;
import java.util.Optional;

public interface ISkillService {
  Skill save(Skill skill);
  Optional<Skill> findById(Long id);
  List<Skill> findAll();
  void deleteById(Long id);

}
