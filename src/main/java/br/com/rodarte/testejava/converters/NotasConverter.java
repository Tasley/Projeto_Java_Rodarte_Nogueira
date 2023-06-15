package br.com.rodarte.testejava.converters;

import br.com.rodarte.testejava.DTO.NotasDTO;
import br.com.rodarte.testejava.entity.Notas;
import org.springframework.stereotype.Component;

@Component
public class NotasConverter {

    public NotasDTO entityToDTO(Notas notas) {
        final var notasDTO = new NotasDTO();
        notasDTO.setId(notas.getId());
        notasDTO.setNome(notas.getNome());
        notasDTO.setDataNascimento(notas.getDataNascimento());
        notasDTO.setSexo(notas.getSexo());
        notasDTO.setNotaTrimestreUm(notas.getNotaTrimestreUm());
        notasDTO.setNotaTrimestreDois(notas.getNotaTrimestreDois());
        notasDTO.setNotaTrimestreTres(notas.getNotaTrimestreTres());
        return notasDTO;
    }

    public Notas DTOToEntity(NotasDTO dto) {
        Notas notas = new Notas();
        notas.setId(dto.getId());
        notas.setNome(dto.getNome());
        notas.setSexo(dto.getSexo());
        notas.setDataNascimento(dto.getDataNascimento());
        notas.setNotaTrimestreUm(dto.getNotaTrimestreUm());
        notas.setNotaTrimestreDois(dto.getNotaTrimestreDois());
        notas.setNotaTrimestreTres(dto.getNotaTrimestreTres());
        return notas;
    }
}