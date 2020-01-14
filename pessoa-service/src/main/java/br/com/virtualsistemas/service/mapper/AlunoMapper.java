package br.com.virtualsistemas.service.mapper;

import br.com.virtualsistemas.domain.Aluno;
import br.com.virtualsistemas.service.dto.AlunoDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface AlunoMapper {

    Aluno toEntity(AlunoDTO dto);

    AlunoDTO toDto(Aluno entity);

}
