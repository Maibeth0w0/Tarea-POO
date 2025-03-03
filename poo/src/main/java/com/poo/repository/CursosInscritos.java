package com.poo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursosInscritos extends JpaRepository<CursosInscritos, Integer> {

}
