package com.poo.repository;
import com.poo.persistencia.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosInscritosRepository extends JpaRepository<CursosInscritos, Long> {

}
