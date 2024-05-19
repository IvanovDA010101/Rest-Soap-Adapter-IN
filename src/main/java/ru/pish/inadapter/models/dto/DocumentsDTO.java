package ru.pish.inadapter.models.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DocumentsDTO {
    @NotBlank
    private String Type;
    @NotBlank
    private String Description;
    @NotBlank
    private String Number;
    private String RegistrationDate;


}