package br.com.rodarte.testejava.repository;

import br.com.rodarte.testejava.entity.Notas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotasRepository extends JpaRepository<Notas, Long> {

    @Query(value = "SELECT identificacao AS identificacao, nome AS nome, " +
            "FORMAT((SUM(nota_trimestre_um+nota_trimestre_dois+nota_trimestre_tres)/3),2) AS media, " +
            "FLOOR(DATEDIFF(CURDATE(), data_nascimento) / 365) AS idade " +
            "FROM notas.t_notas " +
            "GROUP BY nome, data_nascimento, identificacao " +
            "ORDER BY idade ASC", nativeQuery = true)
    List<TabelaNotasDTO> tabelaOrganizada();

    interface TabelaNotasDTO {
        String getIdentificacao();

        String getNome();

        double getMedia();

        int getIdade();
    }
}
