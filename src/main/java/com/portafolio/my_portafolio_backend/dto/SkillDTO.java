package com.portafolio.my_portafolio_backend.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data

public class SkillDTO {



    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Level percentage is required")
    @Min(value = 0, message = "Level percentage must be greater than or equal to 0")
    @Max(value=100, message = "Level percentage must be less than or equal to 100")
    private Integer levelPercentage;
    @NotBlank(message = "Icon class is required")
    private String iconClass;

    private Long personalInfoId;

}
