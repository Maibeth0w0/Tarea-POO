package com.poo.modelos;

import java.io.Serializable;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING)
public class Persona implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "nombres", length = 100)
    private String nombres;
    
    @Column(name = "apellidos", length = 100)
    private String apellidos;
    
    @Column(name = "email", unique = true, length = 100)
    private String email;
    
    public Persona() {
    }
    
    public Persona(String nombres, String apellidos, String email) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
    
    @Override
    public String toString() {
        return new StringBuilder()
            .append("Persona{id=").append(id)
            .append(", nombres='").append(nombres)
            .append("', apellidos='").append(apellidos)
            .append("', email='").append(email)
            .append('}').toString();
    }
}