package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.repository.IPersonalInfoRepository;
import com.portafolio.my_portafolio_backend.service.IPersonalInfoService;
import com.portafolio.my_portafolio_backend.service.ISkillService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-personal-info")
public class PersonalInfoTestController {

    private final IPersonalInfoService personalInfoService;


    public PersonalInfoTestController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }


    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoService.findAll();
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

    @PostMapping
    public ResponseEntity<PersonalInfo> createPersonal(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo =personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.OK);
    }


}
