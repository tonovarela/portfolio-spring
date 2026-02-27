package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.service.ISkillService;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/all")
    public List<Skill> getAll() {
        return skillService.findAll();
    }


    @GetMapping("/id/{id}")
    public Skill getById(@PathVariable Long id) {
        Optional<Skill> skillOptional = skillService.findById(id);
        System.out.println("Skill Optional: " + skillOptional);

        if (skillOptional.isPresent()){
            return skillOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Skill not found with id: " + id);
        }
    }

    @PostMapping
    public Skill create(@RequestBody Skill skill) {
        return skillService.save(skill);
    }





 }
