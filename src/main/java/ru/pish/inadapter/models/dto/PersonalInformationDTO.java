package ru.pish.inadapter.models.dto;

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
public class PersonalInformationDTO {
    @NotBlank
    private String JobNumber;

    private List<ContactsDTO> Contacts;

    private List<SkillsDTO> Skills;

}