package com.poo.modelos;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Profesor extends Persona {
    @Id
    private String tipoContrato;
    
    public Profesor() {
    }
    
    public Profesor(Double id, String nombres, String apellidos, String email, String tipoContrato) {
        super(id, nombres, apellidos, email);
        this.tipoContrato = tipoContrato;
    }
    
    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    
    @Override
    public String toString() {
        return "Profesor{" +
               "tipoContrato='" + tipoContrato + '\'' +
               ", " + super.toString() +
               '}';
    }
}
