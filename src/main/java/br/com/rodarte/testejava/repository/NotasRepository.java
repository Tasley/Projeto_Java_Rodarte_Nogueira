package br.com.rodarte.testejava.repository;

import br.com.rodarte.testejava.entity.Notas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotasRepository extends JpaRepository<Notas, Long> {
}
