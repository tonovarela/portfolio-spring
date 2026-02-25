package com.portafolio.my_portafolio_backend.rest;

import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.repository.IPersonalInfoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/test-personal-info")
public class PersonalInfoTestController {


    private final IPersonalInfoRepository personalInfoRepository;

    public PersonalInfoTestController(IPersonalInfoRepository personalInfoRepository) {
        this.personalInfoRepository = personalInfoRepository;
    }
    @GetMapping("/all")
    public List<PersonalInfo> getAllPersonalInfo() {
        return personalInfoRepository.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo getPersonalInfoById(@PathVariable Long id) {
       Optional<PersonalInfo> personalInfoOptional = personalInfoRepository.findById(id);
       if (personalInfoOptional.isPresent()){
           return personalInfoOptional.get();
       } else {
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, "PersonalInfo not found with id: " + id);
       }
    }




}
