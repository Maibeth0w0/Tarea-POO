package com.poo.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poo.modelos.CursoProfesor;
import com.poo.persistencia.CursosProfesores;
import com.poo.repository.CursosProfesoresRepository;

@Service
public class CursosProfesoresService {

    @Autowired
    private CursosProfesoresRepository cursosProfesoresRepository;

    public List<CursosProfesores> obtenerTodosLosCursosProfesores() {
        return cursosProfesoresRepository.findAll();
    }

    public Optional<CursosProfesores> obtenerCursoProfesorPorId(CursoProfesor cursoProfesor) {
        return cursosProfesoresRepository.findById(cursoProfesor);
    }

    public CursosProfesores guardarCursoProfesor(CursosProfesores cursoProfesor) {
        return cursosProfesoresRepository.save(cursoProfesor);
    }

    public void eliminarCursoProfesor(CursoProfesor cursoProfesor) {
        cursosProfesoresRepository.deleteById(cursoProfesor);
    }

    public CursosProfesores actualizarCursoProfesor(CursosProfesores cursoProfesor) {
        return cursosProfesoresRepository.save(cursoProfesor);
    }
}
