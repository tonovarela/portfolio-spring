package com.portafolio.my_portafolio_backend.controller;


import com.portafolio.my_portafolio_backend.service.interfaces.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

   private final IPersonalInfoService personalInfoService;
   private final IEducationService educationService;
   private final ISkillService skillService;
   private final IExperienceService experienceService;
   private final IProjectService projectService;

      @GetMapping("/")
    public String showIndex(Model model){
          model.addAttribute("personalInfo", personalInfoService.findAll().getFirst());
          model.addAttribute("educationList", educationService.findAll());
          model.addAttribute("skillsList", skillService.findAll());
          model.addAttribute("experienceList", experienceService.findAll());
          model.addAttribute("projectList", projectService.findAll());
          System.out.println("Projects Info: " + projectService.findAll());
          System.out.println("IndexController: showIndex() called");
      return "index";
      }
}
