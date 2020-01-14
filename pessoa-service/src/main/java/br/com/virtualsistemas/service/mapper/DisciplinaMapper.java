package br.com.virtualsistemas.service.mapper;

import br.com.virtualsistemas.domain.Disciplina;
import br.com.virtualsistemas.service.dto.DisciplinaDTO;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring", uses = {})
public interface DisciplinaMapper {

    Disciplina toEntity(DisciplinaDTO dto);

    DisciplinaDTO toDto(Disciplina entity);

}
