package com.portafolio.my_portafolio_backend.mappers;

import com.portafolio.my_portafolio_backend.dto.ProjectDTO;
import com.portafolio.my_portafolio_backend.model.Project;

public class ProjectMapper {
 public static ProjectDTO toDTO(Project project) {
     if (project == null) {
         return null;
     }
     ProjectDTO projectDTO = new ProjectDTO();
     projectDTO.setId(project.getId());
     projectDTO.setTitle(project.getTitle());
     projectDTO.setDescription(project.getDescription());
     projectDTO.setProjectUrl(project.getProjectUrl());
     projectDTO.setPersonalInfoId(project.getPersonalInfoId());

     return projectDTO;
 }

 public static Project toEntity(ProjectDTO projectDTO) {
     if (projectDTO == null) {
         return null;
     }
     Project project = new Project();
     project.setId(projectDTO.getId());
     project.setTitle(projectDTO.getTitle());
     project.setDescription(projectDTO.getDescription());
     project.setProjectUrl(projectDTO.getProjectUrl());
     project.setPersonalInfoId(projectDTO.getPersonalInfoId());
    return project;
 }

}
