package com.poo.modelos;
import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Facultad implements Serializable{
    private static final long serialVersionUID = 1L;
@Id
    private Integer id;
    private String nombre;
    private Persona decano;

    public Facultad(){
    }

    public Facultad(Integer id, String nombre, Persona decano){
        this.id=id;
        this.nombre = nombre;
        this.decano = decano;
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

    public Persona getDecano(){
        return decano;
    }

    public void setDecano(Persona decano) {
        this.decano = decano;
    }

    @Override
    public String toString(){
        return "Facultad{id=" + id +
        ", nombre=" + nombre +
        ", decano=" + (decano != null ? decano.getNombres() : "Sin decano")+ "}";
    }
}
