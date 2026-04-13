package com.portafolio.my_portafolio_backend.service.implementation;


import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.repository.interfaces.ISkillRepository;
import com.portafolio.my_portafolio_backend.service.interfaces.ISkillService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
public class SkillService implements ISkillService {

    private  final ISkillRepository skillRepository;

    public SkillService(ISkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    @Transactional
    public Skill save(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Skill> findById(Long id) {
        return skillRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return skillRepository.findAll();
    }

    @Override
    @Transactional()
    public void deleteById(Long id) {
        skillRepository.deleteById(id);
    }
}
