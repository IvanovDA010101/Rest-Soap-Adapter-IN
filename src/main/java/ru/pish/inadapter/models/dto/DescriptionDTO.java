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
public class DescriptionDTO {

    @NotBlank
    private String Uuid;
    @NotBlank
    private String Description;

}