package com.portafolio.my_portafolio_backend.service.interfaces;


import com.portafolio.my_portafolio_backend.model.Project;

import java.util.List;

public interface IProjectService {
 List<Project> findAll();

 Project save(Project project);


}
