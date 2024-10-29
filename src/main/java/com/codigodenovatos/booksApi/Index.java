package com.codigodenovatos.booksApi;

import com.codigodenovatos.booksApi.service.ConsumoAPI;
import com.codigodenovatos.booksApi.service.ConvierteDatos;

import java.util.Scanner;

public class Index {

    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();


    public void muestraMenu() {
        System.out.println("""
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                        ¿Que Libro deseas buscar?
                >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                """);
        //Busca los datos generales de las series
        var serieIngresada = input.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + serieIngresada.replace(" ", "+"));
        /*
        var datos = convierteDatos.obtenerDatos(json, DatosSeries.class);
        System.out.println(datos);
        */
    }
}
