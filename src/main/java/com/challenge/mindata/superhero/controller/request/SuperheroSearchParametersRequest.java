package com.challenge.mindata.superhero.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SuperheroSearchParametersRequest {

    @Builder.Default
    private List<String> ids = new ArrayList<>();

    private String superheroName;

}
