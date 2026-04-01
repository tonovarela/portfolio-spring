package com.portafolio.my_portafolio_backend.controller;

import com.portafolio.my_portafolio_backend.exception.ValidationException;
import com.portafolio.my_portafolio_backend.model.PersonalInfo;
import com.portafolio.my_portafolio_backend.service.interfaces.IPersonalInfoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PortfolioController {

    private final IPersonalInfoService personalInfoService;

    public PortfolioController(IPersonalInfoService personalInfoService) {
        this.personalInfoService = personalInfoService;
    }

    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("personalInfo", new PersonalInfo());
        return "form";
    }

    @PostMapping("/personal-info-save")
    public String submitForm(@Valid @ModelAttribute("personalInfo") PersonalInfo personalInfo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult);
        }
        personalInfoService.save(personalInfo);
        return "redirect:/form";
    }
}
