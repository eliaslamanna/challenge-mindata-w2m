package com.challenge.mindata.superhero.controller.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperheroSearchParametersRequest {

    private List<String> ids;

    private String superheroName;

}
