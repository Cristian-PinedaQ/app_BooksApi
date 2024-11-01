package com.codigodenovatos.booksApi;

import com.codigodenovatos.booksApi.model.Datos;
import com.codigodenovatos.booksApi.model.DatosLibros;
import com.codigodenovatos.booksApi.service.ConsumoAPI;
import com.codigodenovatos.booksApi.service.ConvierteDatos;

import java.util.*;
import java.util.stream.Collectors;

public class Index {

    private Scanner input = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/";
    private ConvierteDatos convierteDatos = new ConvierteDatos();


    public void muestraMenu() {
        var json = consumoAPI.obtenerDatos(URL_BASE );
        System.out.println(json);

        var datos = convierteDatos.obtenerDatos(json, Datos.class);

        List<Datos> libros = new ArrayList<>();
        libros.add(datos);
        List<DatosLibros> datosLibros = libros.stream()
                .flatMap(t -> t.results().stream())
                .collect(Collectors.toList());

        datosLibros.stream()
                .limit(5)
                        .forEach(System.out::println);

        System.out.println("""
                
                => Top 10 de paliculas mas descargadas =>
                """);
        datosLibros
                .stream()
                .limit(10)
                .forEach(t -> System.out.println(t.titulo().toUpperCase()));

        var jsonConsulta = consumoAPI.obtenerDatos(URL_BASE + "?languages=es");
        var datosLibrosEspanol = convierteDatos.obtenerDatos(jsonConsulta, Datos.class);

        List<Datos> librosEspanol = new ArrayList<>();
        librosEspanol.add(datosLibrosEspanol);

        List<DatosLibros> datosEspanol = librosEspanol.stream()
                .flatMap(t -> t.results().stream())
                .collect(Collectors.toList());

        System.out.println("""
                
                <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
                        ¿Que Libro deseas buscar?
                >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
                """);
//Buscamos el titulo de un libro por la
        var coinsidenciaTitulo = input.nextLine();
        Optional<DatosLibros> search = datosEspanol.stream()
                .filter(e -> e.titulo().toUpperCase().contains(coinsidenciaTitulo.toUpperCase()))
                .findFirst();
        if (search .isPresent()){
            System.out.println("Libro encontrado");
            System.out.println(search.get());
        }else {
            System.out.println("No existe ninguna coninsidencia, por favor intetelo de nuevo");
        }
//Obtenemos la estadista segun el parametro de descargas por libro
        DoubleSummaryStatistics est = datosLibros.stream()
                .filter(e -> e.descargas() > 0.0)
                .collect(Collectors.summarizingDouble(DatosLibros::descargas));
        System.out.println("Media de descargas " + est.getAverage());
        System.out.println("Meyor numero de descargas " + est.getMax());
        System.out.println("Menor numero de descargas " + est.getMin());
        System.out.println("Numero de registros contados " + datosLibros.size());
    }
}
