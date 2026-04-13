package com.portafolio.my_portafolio_backend.controller;

import com.portafolio.my_portafolio_backend.dto.SkillDTO;
import com.portafolio.my_portafolio_backend.mappers.SkillMapper;
import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.service.interfaces.ISkillService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/skills")
public class SkillController {

    private final ISkillService skillService;

    public SkillController(ISkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping

    public String list(Model model) {
        List<SkillDTO> skillsDTO = skillService.findAll()
                .stream()
                .map(SkillMapper::toDTO)
                .toList();
        model.addAttribute("skills", skillsDTO);
        return "/skill/list";
    }

    @GetMapping("/new")
    public String showForm(Model model) {
        SkillDTO skillDTO = new SkillDTO();
        model.addAttribute("skillDTO", skillDTO);
        return "/skill/form";
    }



}


