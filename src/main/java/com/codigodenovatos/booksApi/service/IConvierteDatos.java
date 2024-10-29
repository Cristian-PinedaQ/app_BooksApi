package com.codigodenovatos.booksApi.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class <T> clase);
}
