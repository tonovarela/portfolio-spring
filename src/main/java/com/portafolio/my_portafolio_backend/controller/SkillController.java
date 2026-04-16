package com.portafolio.my_portafolio_backend.controller;

import com.portafolio.my_portafolio_backend.dto.SkillDTO;
import com.portafolio.my_portafolio_backend.mappers.SkillMapper;
import com.portafolio.my_portafolio_backend.model.Skill;
import com.portafolio.my_portafolio_backend.service.interfaces.ISkillService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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
        model.addAttribute("skillDTO", new SkillDTO());
        return "/skill/form";
    }
    @PostMapping("/save")
    public String saveSkill(@Valid @ModelAttribute("skillDTo") SkillDTO skillDTO, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/skill/form";
        }
        try{
           Skill skill = SkillMapper.toEntity(skillDTO);
           skillService.save(skill);
           return  "redirect:/skills/list";
        }
        catch (Exception e) {
            System.out.println("Error saving skill: " + e.getMessage());
            return "/error-page";
        }

    }

   @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
      Optional<Skill> skillOptions =  skillService.findById(id);
      if  (skillOptions.isEmpty()) {
          model.addAttribute("errorMessage", "Skill not found with id: " + id);
          return "redirect:/skills/list";
      }
       SkillDTO skill = SkillMapper.toDTO(skillOptions.get());
       model.addAttribute("skillDTO", skill);
       return  "/skill/list";

   }
   @GetMapping("/personal/{personalInfoId}")
    public String findByPersonalInfoId(@PathVariable("personalInfoId") Long personalInfoId, Model model) {
        List<SkillDTO> skillsDTO = skillService.findAll()
                .stream()
                .filter(skill -> skill.getPersonalInfoId().equals(personalInfoId))
                .map(SkillMapper::toDTO)
                .toList();
        model.addAttribute("skills", skillsDTO);
        return "/skill/list";
   }

   @PostMapping ("/delete/{skill}")
    public String delete(@PathVariable("skill") Long skillId, RedirectAttributes redirectAttributes) {
        try{
            skillService.deleteById(skillId);
            redirectAttributes.addFlashAttribute("message", "Skill deleted successfully");
        }catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Error deleting skill: " + e.getMessage());
        }
        return "redirect:/skills/list";

   }



 }


