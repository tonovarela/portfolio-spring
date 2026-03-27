package com.portafolio.my_portafolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProjectDTO {
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Description is required")
    private String description;

    private String imageUrl;
    @NotBlank(message = "project is required")
    private String projectUrl;

    @NotNull(message = "personalInfoId is required")
    private Long personalInfoId;

}
