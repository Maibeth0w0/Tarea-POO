package com.poo;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CursosInscritos implements Servicios {
    
    private List<Inscripcion> listadoInscripciones;

    public CursosInscritos(){
        this.listadoInscripciones = new ArrayList<>();
    }

    public void inscribirCurso(Inscripcion inscripcion){
        listadoInscripciones.add(inscripcion);
        System.out.println("curso insrito: "+ inscripcion.getCurso().getNombre());
   }

   public void eliminarInscripcion(Inscripcion inscripcion){
        if (listadoInscripciones.remove(inscripcion)){
            System.out.println("curso eliminado: "+ inscripcion.getCurso().getNombre());
        } else {            
            System.out.println("curso no encontrado en el listado: ");
        }
   }

   public void actualizarCurso(Inscripcion cursoActualizado){
    for (int i = 0; i < listadoInscripciones.size(); i++){
       if ( listadoInscripciones.get(i).getCurso().getId().equals(cursoActualizado.getCurso().getId())){
            listadoInscripciones.set(i, cursoActualizado);
            System.out.println("datos actualizados para: " + cursoActualizado.getCurso().getNombre());
            return;
       }
    }
    System.out.println("curso no encontrado para actualizar.");
   }

    public void guardarInformacion() {
        String filePath = "cursos_profesores.xlsx";
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("CursosInscritos");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Curso");
            headerRow.createCell(1).setCellValue("Estudiante");
            headerRow.createCell(2).setCellValue("Programa");

            int rowNum = 1;
            for (Inscripcion ci : listadoInscripciones) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(ci.getCurso().getNombre());
                row.createCell(1).setCellValue(ci.getEstudiante().getNombres() + " " + ci.getEstudiante().getApellidos());
                row.createCell(2).setCellValue(ci.getCurso().getPrograma().getNombre());
            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            System.out.println("Datos guardados en la hoja 'CursosInscritos'.");
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void cargarDatos() {
        String filePath = "cursos_profesores.xlsx";
        try (FileInputStream fis = new FileInputStream(filePath);
            Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet("CursosInscritos");
            if (sheet == null) {
                System.out.println("La hoja 'CursosInscritos' no existe en el archivo.");
                return;
            }

            listadoInscripciones.clear();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    String nombreCurso = row.getCell(0).getStringCellValue();
                    String nombreCompletoEstudiante = row.getCell(1).getStringCellValue();
                    String nombrePrograma = row.getCell(2).getStringCellValue();

                    Programa programa = new Programa();
                    programa.setNombre(nombrePrograma);

                    Curso curso = new Curso();
                    curso.setNombre(nombreCurso);
                    curso.setPrograma(programa);

                    Estudiante estudiante = new Estudiante();
                    String[] partes = nombreCompletoEstudiante.split(" ", 2);
                    if (partes.length == 2) {
                        estudiante.setNombres(partes[0]);
                        estudiante.setApellidos(partes[1]);
                    } else {
                        estudiante.setNombres(nombreCompletoEstudiante);
                        estudiante.setApellidos("");
                    }

                    Inscripcion inscripcion = new Inscripcion(curso, 2023, 1, estudiante);

                    listadoInscripciones.add(inscripcion);
                }
            }

            System.out.println("Datos cargados desde la hoja 'CursosInscritos'.");

        } catch (IOException e) {
            e.printStackTrace();
        }
}


    @Override
    public String toString() {
        return "CursosInscritos {"+
        "listadoInscripciones=" + listadoInscripciones + 
        ", toString()=" + super.toString()
        + "}";
    }

    @Override
    public String imprimirPosición(int posición) {
        if (posición >= 0 && posición < listadoInscripciones.size()) {
            return listadoInscripciones.get(posición).toString();
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


    
}
