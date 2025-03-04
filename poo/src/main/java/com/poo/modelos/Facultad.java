package com.poo.modelos;
import java.io.Serializable;

import jakarta.persistence.*;

@Entity
public class Facultad implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "id_decano")
    private Profesor decano;

    public Facultad(){
    }

    public Facultad(String nombre, Profesor decano){
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

    public void setDecano(Profesor decano) {
        this.decano = decano;
    }

    @Override
    public String toString(){
        return "Facultad{id=" + id +
        ", nombre=" + nombre +
        ", decano=" + (decano != null ? decano.getNombres() : "Sin decano")+ "}";
    }
}
