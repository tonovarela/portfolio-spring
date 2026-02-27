package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/skills")
public class SkillsController {
    private final ISkillService skillService;
    public SkillsController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping("/id/{id}")
    public Skill getById(@PathVariable Long id) {
        Optional<Skill> skillOptional = skillService.findById(id);
        if (skillOptional.isPresent()){
            return skillOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found with id: " + id);
        }
    }

    @GetMapping("/all")
    public List<Skill> getAll() {
        return skillService.findAll();
    }


    @PostMapping
    public Skill create(@RequestBody Skill skill) {
        return skillService.save(skill);
    }


    @PutMapping("/id/{id}")
    public Skill update(@PathVariable Long id, @RequestBody Skill skill) {
        Optional<Skill> existingSkillOptional = skillService.findById(id);
        if (existingSkillOptional.isPresent()) {
            Skill existingSkill = existingSkillOptional.get();
            existingSkill.setName(skill.getName());
            existingSkill.setLevelPercentage(skill.getLevelPercentage());
            existingSkill.setIconClass(skill.getIconClass());
            existingSkill.setPersonalInfoId(skill.getPersonalInfoId());
            return skillService.save(existingSkill);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found with id: " + id);
        }
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Skill> existingSkillOptional = skillService.findById(id);
        if (existingSkillOptional.isPresent()) {
            skillService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found with id: " + id);
        }
    }





 }
