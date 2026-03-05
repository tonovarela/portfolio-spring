package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.Education;
import com.portafolio.my_portafolio_backend.service.IEducationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/education")
public class EducationController {

    private final IEducationService educationService;

    public EducationController(IEducationService educationService) {
        this.educationService = educationService;
    }

   @GetMapping("/all")
    public ResponseEntity<List<Education>> All(){
        return new ResponseEntity<>(educationService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Education> getById(@PathVariable  Long id) {
        Optional<Education> educationOptional = this.educationService.findById(id);
        if (educationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found with id: " + id);
        }
        return new ResponseEntity<>(educationOptional.get(), HttpStatus.OK);
    }

   @PostMapping("/create")
    public ResponseEntity<Education> create(@RequestBody Education education) {
        Optional<Education> educationOptional=  educationService.findById(education.getId());
        if (educationOptional.isEmpty()){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Education with id: " + education.getId() + " already exists");
        }
        Education  educationSaved = this.educationService.save(education);
        return new ResponseEntity<>(educationSaved, HttpStatus.OK);
    }
   @DeleteMapping("/id/{id}")
     public ResponseEntity<Void> delete(@PathVariable  Long id) {
        Optional<Education> existingEducationOptional = this.educationService.findById(id);
        if (existingEducationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found with id: " + id);
        }
        this.educationService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/id/{id}")
    public ResponseEntity<Education> update(@PathVariable Long id, @RequestBody  Education education) {
        Optional<Education> existingEducationOptional = this.educationService.findById(id);
        if (existingEducationOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Education not found with id: " + id);
        }
        Education existingEducation = educationService.save(existingEducationOptional.get());
        return new ResponseEntity<>(existingEducation, HttpStatus.OK);
    }
}
