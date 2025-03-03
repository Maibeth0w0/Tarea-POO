package com.poo.modelos;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Programa implements Serializable{
    @Id
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String nombre;
    private Double duracion;
    private String registro;
    private Facultad facultad;

    public Programa(){
    }

    public Programa( Integer id, String nombre, Double duracion, String registro, Facultad facultad){
        this.id = id;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getNombre(){
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public Double getDuracion(){
        return duracion;
    }

    public void setDuracion(Double duracion){
        this.duracion = duracion;
    }

    public String getRegistro(){
        return registro;
    }

    public void setRegistro(String registro){
        this.registro = registro;
    }

    public Facultad getFacultad(){
        return facultad;
    }

    public void setFacultad(Facultad facultad){
        this.facultad=facultad;
    }

    @Override
    public String toString(){
        return "Programa{Id=" + id +
        ", nombre=" + nombre +
        ", duración=" + duracion +
        ", registro=" + registro +
        ", facultad=" + facultad.getNombre() +
        "}";
    }

}
