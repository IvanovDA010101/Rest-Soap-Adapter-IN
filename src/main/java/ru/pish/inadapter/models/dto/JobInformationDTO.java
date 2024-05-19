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
public class JobInformationDTO {
    @NotBlank
    private String WorkType;
    private String ChiefId;
    @NotBlank
    private DescriptionDTO Department;
    @NotBlank
    private DescriptionDTO Position;
    @NotBlank
    private SalaryDTO Salary;
    @NotBlank
    private String EntryDate;
    private String EndDate;
}