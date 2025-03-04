package com.poo.persistencia;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.crypto.*;
import com.poo.modelos.*;
import com.poo.servicios.Servicios;
import jakarta.persistence.*;

@Entity
public class CursosProfesores implements Servicios {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cursos_profesores_id")
    private List<CursoProfesor> listadoCursoProfesores = new ArrayList<>();

    private static final String FILE_PATH = "cursosProfesores.bin";
    private static final String KEY_PATH = "cursosProfesores.key";
    private SecretKey secretKey;

    public CursosProfesores() {
        this.listadoCursoProfesores = new ArrayList<>();
        cargarClave(); 
    }

    public List<CursoProfesor> getListadoCursoProfesores() {
        return listadoCursoProfesores;
    }

    public void inscribirCursoProfesores(CursoProfesor cursoProfesor) {
        listadoCursoProfesores.add(cursoProfesor);
        System.out.println("Curso " + cursoProfesor.getCurso().getNombre() + " asignado exitosamente al profesor " + cursoProfesor.getProfesor().getNombres() + ".");
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

    public Profesor obtenerProfesorPorNombre(String nombreProfesor) {
    for (CursoProfesor cp : listadoCursoProfesores) {
        if (cp.getProfesor().getNombres().equals(nombreProfesor)) {
            return cp.getProfesor();
        }
    }
    return null;
    }

    public Curso obtenerCursoPorNombre(String nombreCurso) {
        for (CursoProfesor cp : listadoCursoProfesores) {
            if (cp.getCurso().getNombre().equals(nombreCurso)) {
            return cp.getCurso();
            }
        }
    return null;
    }


    public void guardarInformacion() {
        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            try (CipherOutputStream cos = new CipherOutputStream(new FileOutputStream(FILE_PATH), cipher);
                ObjectOutputStream oos = new ObjectOutputStream(cos)) {

                oos.writeObject(listadoCursoProfesores);
                System.out.println("Datos guardados correctamente en archivo binario cifrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al guardar la información: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void cargarDatos() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println("No hay datos previos guardados.");
            return;
        }

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            try (CipherInputStream cis = new CipherInputStream(new FileInputStream(FILE_PATH), cipher);
                ObjectInputStream ois = new ObjectInputStream(cis)) {

                listadoCursoProfesores = (List<CursoProfesor>) ois.readObject();
                System.out.println("Datos cargados correctamente desde el archivo binario cifrado.");
            }
        } catch (Exception e) {
            System.err.println("Error al cargar la información: " + e.getMessage());
        }
    }

    @Override
    public String toString() {
        return "CursosProfesores [listadoCursoProfesores=" + listadoCursoProfesores + "]";
    }

    @Override
    public String imprimirPosicion(int posicion) {
        if (posicion >= 0 && posicion < listadoCursoProfesores.size()) {
            return listadoCursoProfesores.get(posicion).toString();
        }
        return "Posición fuera de rango.";
    }

    @Override
    public Integer cantidadActual() {
        return listadoCursoProfesores.size();
    }

    @Override
    public List<String> imprimirListado() {
        List<String> nombres = new ArrayList<>();
        for (CursoProfesor cursoProfesor : listadoCursoProfesores) {
            nombres.add(cursoProfesor.getProfesor().getNombres());
        }
        return nombres;
    }

    public void setListadoCursoProfesores(List<CursoProfesor> listadoCursoProfesores) {
        this.listadoCursoProfesores = listadoCursoProfesores;
    }
}
