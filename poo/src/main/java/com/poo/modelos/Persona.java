package com.poo.modelos;
import java.io.Serializable;

public class Persona implements Serializable { // Implementamos Serializable
    private static final long serialVersionUID = 1L; // Asegura compatibilidad en la serialización
    
    private Double id;
    private String nombres;
    private String apellidos;
    private String email;
    
    public Persona() {
    }
    
    public Persona(Double id, String nombres, String apellidos, String email) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }
    
    public Double getId() {
        return id;
    }

    public void setId(Double id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    // Método toString()
    @Override
    public String toString() {
        return "Persona{" +
               "id=" + id +
               ", nombres='" + nombres + '\'' +
               ", apellidos='" + apellidos + '\'' +
               ", email='" + email + '\'' +
               '}';
    }
}

