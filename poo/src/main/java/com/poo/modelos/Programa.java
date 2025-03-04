package com.poo.modelos;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
public class Programa implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    private String nombre;
    
    private Double duracion;
    
    @Column(unique = true)
    private String registro;
    
    @ManyToOne
    @JoinColumn(name = "id_facultad")
    private Facultad facultad;

    public Programa(){
    }

    public Programa(String nombre, Double duracion, String registro, Facultad facultad) {
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
    public String toString() {
        return "Programa{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", duracion=" + duracion +
            ", registro='" + registro + '\'' +
            ", facultad=" + (facultad != null ? facultad.getNombre() : "Sin facultad") +
            '}';
    }
}
