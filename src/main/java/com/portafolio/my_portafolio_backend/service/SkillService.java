package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.repository.ISkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SkillService implements  ISkillService {

    private  final ISkillRepository skillRepository;
    public SkillService(ISkillRepository skillRepository){
        this.skillRepository = skillRepository;
    }

    @Override
    public Skill save(Skill skill) {
        return null;
    }

    @Override
    public Optional<Skill> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {

    }
}
