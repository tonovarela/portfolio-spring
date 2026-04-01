package com.portafolio.my_portafolio_backend.api;

import com.portafolio.my_portafolio_backend.model.Experience;
import com.portafolio.my_portafolio_backend.service.interfaces.IExperienceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/experience")
public class ExperienceController {

    private final IExperienceService experienceService;


    public ExperienceController(IExperienceService experienceService) {
        this.experienceService = experienceService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Experience> getById(@PathVariable Long id) {
        Optional<Experience> experience = experienceService.findById(id);
        if (experience.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found with id: " + id);
        }
        return new ResponseEntity<>(experience.get(), HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<Experience> getAll() {
        return experienceService.findAll();
    }

    @PostMapping("/create")
    public ResponseEntity<Experience> create(@RequestBody Experience experience) {
        Experience newExperience = experienceService.save(experience);
        return new ResponseEntity<>(newExperience, HttpStatus.OK);
    }
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Optional<Experience> existingExperienceOptional = experienceService.findById(id);
        if (existingExperienceOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found with id: " + id);
        }
        experienceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Experience> update(@PathVariable Long id, @RequestBody Experience experience) {
        Optional<Experience> existingExperienceOptional = experienceService.findById(id);
        if (existingExperienceOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Experience not found with id: " + id);
        }
        experienceService.save(existingExperienceOptional.get());
        return new ResponseEntity<>(existingExperienceOptional.get(), HttpStatus.OK);
    }

}
