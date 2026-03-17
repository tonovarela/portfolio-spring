package com.portafolio.my_portafolio_backend.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Experience {
    private Long id;
    @NotBlank(message = "Job title is required")
    private String jobTitle;
    @NotBlank(message = "Company name is required")
    private String companyName;

    @NotNull(message = "Start date is required")
    @PastOrPresent(message = "Start date must be in the past or present")
    private LocalDate startDate;

    @PastOrPresent(message = "End date must be in the past or present")
    private LocalDate endDate;

    @NotBlank(message = "Description is required")
    private String description;

    private Long personalInfoId;




}
