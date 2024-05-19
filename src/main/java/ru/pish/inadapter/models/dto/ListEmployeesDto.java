package ru.pish.inadapter.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class ListEmployeesDto {

    private List<EmployeeDTO> employees;
}
