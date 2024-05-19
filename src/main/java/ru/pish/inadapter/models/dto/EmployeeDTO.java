package ru.pish.inadapter.models.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDTO
{
    @NotBlank
    private String Uuid;
    @NotBlank
    private String FirstName;
    @NotBlank
    private String LastName;

    private String MiddleName;
    @NotBlank
    private String Birthdate;

    private JobInformationDTO JobInformation;

    private PersonalInformationDTO PersonalInformation;

}