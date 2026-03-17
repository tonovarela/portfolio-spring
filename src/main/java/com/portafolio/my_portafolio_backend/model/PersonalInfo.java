package com.portafolio.my_portafolio_backend.model;





import jakarta.validation.constraints.*;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

@Data
@NoArgsConstructor
@AllArgsConstructor


public class PersonalInfo {
    private Long id;


    @NotBlank(message = "First name is required")
    private String firstName;
   @NotBlank(message = "Last name is required")
    private String lastName;
   @NotBlank(message = "Title is required")
    private String title;
   @NotBlank(message = "Profile description is required")
    private String profileDescription;
   @NotBlank(message = "Profile image URL is required")
    private String profileImageUrl;
   @Min(value = 0, message = "Years of experience must be greater than or equal to 0")
   @Max(value=50, message = "Years of experience must be less than or equal to 50")
   @NotNull(message = "Years of experience is required")
    private Integer yearsOfExperience;
   @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

   @NotBlank(message = "Phone number is required")
    private String phone;
   @NotBlank(message = "linkedin  url is required")
   @URL(message = "LinkedIn URL should be valid")
    private String linkedinUrl;

    @URL(message = "github url should be valid")
    @NotBlank(message = "github url is required")
    private String githubUrl;
}
