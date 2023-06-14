package br.com.rodarte.testejava.repository;

import br.com.rodarte.testejava.entity.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotasRepository extends JpaRepository<Notas, Long> {

    @Query(value = "SELECT nome as nome, " +
            "format((sum(nota_trimestre_um+nota_trimestre_dois+nota_trimestre_tres)/3),2) as media, " +
            "floor(datediff(curdate(), data_nascimento) / 365) as idade " +
            "from notas.t_notas " +
            "group by nome, data_nascimento " +
            "order by idade ASC")
    List<Notas> tabelaOrganizada();

}
