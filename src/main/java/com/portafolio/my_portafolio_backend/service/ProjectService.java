package com.portafolio.my_portafolio_backend.service;

import com.portafolio.my_portafolio_backend.model.Project;
import com.portafolio.my_portafolio_backend.repository.interfaces.IProjectRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProjectService implements IProjectService {
    private final IProjectRepository projectRepository;


    public ProjectService(IProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }
    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return projectRepository.findAll();
    }


    @Override
    @Transactional
    public Project save(Project project) {
        return projectRepository.save(project);
    }
}
