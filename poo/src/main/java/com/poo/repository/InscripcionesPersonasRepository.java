package com.poo.repository;
import com.poo.modelos.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InscripcionesPersonasRepository extends JpaRepository<Persona, Integer> {

}
