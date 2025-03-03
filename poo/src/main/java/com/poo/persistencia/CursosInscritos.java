package com.poo.persistencia;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.crypto.*;
import jakarta.persistence.*;

import com.poo.modelos.Inscripcion;
import com.poo.servicios.Servicios;

@Entity
public class CursosInscritos implements Servicios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cursos_inscritos_id")
    private List<Inscripcion> listadoInscripciones = new ArrayList<>();

    private static final String FILE_PATH = "cursos_inscritos.bin";
    private static final String KEY_PATH = "cursos_inscritos.key";
    private SecretKey secretKey;

    public CursosInscritos(){
        this.listadoInscripciones = new ArrayList<>();
        cargarClave();
        cargarDatos();
    }

    public void inscribirCurso(Inscripcion inscripcion){
        if (existeInscripcion(inscripcion)) {
            System.out.println("Error: El curso ya está inscrito.");
            return;
        }
        listadoInscripciones.add(inscripcion);
        guardarInformacion();
        System.out.println("Curso inscrito y guardado: " + inscripcion.getCurso().getNombre());
    }

    private boolean existeInscripcion(Inscripcion inscripcion) {
        for (Inscripcion i : listadoInscripciones) {
            if (i.getCurso().getId().equals(inscripcion.getCurso().getId())) {
                return true;
            }
        }
        return false;
    }

    public void eliminarInscripcion(Inscripcion inscripcion){
        if (listadoInscripciones.remove(inscripcion)){
            System.out.println("Curso eliminado: " + inscripcion.getCurso().getNombre());
        } else {
            System.out.println("Curso no encontrado en el listado.");
        }
    }

    public void actualizarCurso(Inscripcion cursoActualizado){
        for (int i = 0; i < listadoInscripciones.size(); i++){
            if (listadoInscripciones.get(i).getCurso().getId().equals(cursoActualizado.getCurso().getId())){
                listadoInscripciones.set(i, cursoActualizado);
                System.out.println("Datos actualizados para: " + cursoActualizado.getCurso().getNombre());
                return;
            }
        }
        System.out.println("Curso no encontrado para actualizar.");
    }

    private void generarClave() {
        try {
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128);
            secretKey = keyGen.generateKey();
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(KEY_PATH))) {
                oos.writeObject(secretKey);
            }
        } catch (NoSuchAlgorithmException | IOException e) {
            System.err.println("Error generando la clave de cifrado: " + e.getMessage());
        }
    }

    private void cargarClave() {
        File keyFile = new File(KEY_PATH);
        if (keyFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(KEY_PATH))) {
                secretKey = (SecretKey) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error cargando la clave de cifrado: " + e.getMessage());
            }
        } else {
            generarClave();
        }
    }

    public void guardarInformacion() {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(FILE_PATH), cipher);
                ObjectOutputStream oos = new ObjectOutputStream(cos)) {

                oos.writeObject(listadoInscripciones);
                System.out.println("Datos guardados en archivo binario cifrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al guardar la información: " + e.getMessage());
        }
    }

    public void cargarDatos() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No hay datos previos para cargar.");
            return;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            try (CipherInputStream cis = new CipherInputStream(new FileInputStream(FILE_PATH), cipher);
                ObjectInputStream ois = new ObjectInputStream(cis)) {

                listadoInscripciones = (List<Inscripcion>) ois.readObject();
                System.out.println("Datos cargados desde archivo binario cifrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la información: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "CursosInscritos {" +
                "listadoInscripciones=" + listadoInscripciones + 
                "}";
    }

    @Override
    public String imprimirPosicion(int posicion) {
        if (posicion >= 0 && posicion < listadoInscripciones.size()) {
            return listadoInscripciones.get(posicion).toString();
        }
        return "Posición fuera de rango.";
    }

    @Override
    public Integer cantidadActual() {
        return listadoInscripciones.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> nombres = new ArrayList<>();
        for (Inscripcion inscripcion : listadoInscripciones) {
            nombres.add(inscripcion.getCurso().getNombre());
        }
        return nombres;
    }

    public List<Inscripcion> getListadoInscripciones() {
        return listadoInscripciones;
    }

    public void setListadoInscripciones(List<Inscripcion> listadoInscripciones) {
        this.listadoInscripciones = listadoInscripciones;
    }


}
