package ru.pish.inadapter.models.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDTO {
    private String street;
    private String city;
    private String state;
    private String zip;

}