package com.portafolio.my_portafolio_backend.controller;

import com.portafolio.my_portafolio_backend.dto.ProjectDTO;
import com.portafolio.my_portafolio_backend.mappers.ProjectMapper;
import com.portafolio.my_portafolio_backend.model.Project;
import com.portafolio.my_portafolio_backend.service.FileStorageService;
import com.portafolio.my_portafolio_backend.service.IProjectService;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    @GetMapping("/")
    public String getAll(Model model) {
        List<ProjectDTO> projects = projectService.findAll()
                .stream()
                .map(ProjectMapper::toDTO)
                .toList();
        model.addAttribute("projects", projects);
        return "projects/list";
    }




    @PostMapping("/save")
    public String saveProject(@Valid @ModelAttribute("projects") ProjectDTO projectDTO,
                              @RequestParam("file") MultipartFile file) throws Exception {
        Optional<String> ImageUrl = fileStorageService.storeFile(file);
       if  (ImageUrl.isEmpty()) {
           return "error-page";
       }
        ImageUrl.ifPresent(projectDTO::setProjectUrl);
        Project project = ProjectMapper.toEntity(projectDTO);
        projectService.save(project);
        return "redirect:/projects/list";

    }




}
