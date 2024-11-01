package com.codigodenovatos.booksApi.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Autores(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") String fechaNacimiento
) {
}
