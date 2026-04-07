package com.portafolio.my_portafolio_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

@Setter
@Getter
public class ProjectDTO {
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Description is required")
    private String description;
    private String imageUrl;

    @URL(message = "Project URL must be a valid URL")
    @NotBlank(message = "Project URL is required")
    private String projectUrl;

    @NotNull(message = "personalInfoId is required")
    @Min(value = 1, message = "personalInfoId must be a positive number")
    private Long personalInfoId;


}
