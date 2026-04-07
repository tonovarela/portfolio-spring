package com.portafolio.my_portafolio_backend.controller;

import com.portafolio.my_portafolio_backend.dto.ProjectDTO;
import com.portafolio.my_portafolio_backend.mappers.ProjectMapper;
import com.portafolio.my_portafolio_backend.model.Project;
import com.portafolio.my_portafolio_backend.service.implementation.FileStorageService;
import com.portafolio.my_portafolio_backend.service.interfaces.IProjectService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/projects")
public class ProjectController {

    private final IProjectService projectService;

    private final FileStorageService fileStorageService;


    public ProjectController(IProjectService projectService, FileStorageService fileStorageService) {
        this.projectService = projectService;
        this.fileStorageService = fileStorageService;
    }
    @GetMapping
    public String getAll(Model model) {
        System.out.println("Getting all projects");
        List<ProjectDTO> projects = projectService.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
        model.addAttribute("projects", projects);
        return "projects/list";
    }


    @GetMapping("/new")
    public String showForm(Model model) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setPersonalInfoId(1L);
        model.addAttribute("projectDTO", projectDTO);
        return "projects/form";
    }



    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("projectDTO") ProjectDTO projectDTO,
                              BindingResult result,
                              @RequestParam(value = "file", required = false) MultipartFile file) {
        if (file.isEmpty()){
            result.rejectValue("imageUrl", "error.required", "Please select an image url");
        }
        if (result.hasErrors()){
            return "projects/form";
        }
        //if (file != null && !file.isEmpty()) {
            Optional<String> imageUrl = fileStorageService.storeFile(file);
            if (imageUrl.isEmpty()) {
                return "error-page";
            }
            imageUrl.ifPresent(projectDTO::setImageUrl);
        //}
        Project project = ProjectMapper.toEntity(projectDTO);
        projectService.save(project);
        return "redirect:/projects";

    }




}
