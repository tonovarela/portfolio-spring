package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.service.IPersonalInfoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/personal-info")
public class PersonalInfoController {
    private final IPersonalInfoService personalInfoService;

    public PersonalInfoController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }


    @GetMapping("/id/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id) {
        Optional<PersonalInfo> personalInfoOptional = personalInfoService.findById(id);
        if (personalInfoOptional.isPresent()){
            return personalInfoOptional.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
        }
    }
    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoService.findAll();
    }



    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonal(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo =personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.OK);
    }

    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> deletePersonalInfo(@PathVariable Long id) {
        Optional<PersonalInfo> existingPersonalInfoOptional = personalInfoService.findById(id);
        if (existingPersonalInfoOptional.isPresent()) {
            personalInfoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
        }
    }


}
