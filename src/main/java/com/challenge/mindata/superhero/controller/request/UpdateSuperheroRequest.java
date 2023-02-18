package com.challenge.mindata.superhero.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSuperheroRequest {

    private String name;

    private String superheroName;

}
