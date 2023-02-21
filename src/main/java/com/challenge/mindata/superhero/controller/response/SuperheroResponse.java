package com.challenge.mindata.superhero.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuperheroResponse {

    private Long id;

    private String name;

    private String superheroName;

}
