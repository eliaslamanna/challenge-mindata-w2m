package com.challenge.mindata.superhero.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSuperheroRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String superheroName;

}
