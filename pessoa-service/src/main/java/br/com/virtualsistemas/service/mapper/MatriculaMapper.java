package br.com.virtualsistemas.service.mapper;

import br.com.virtualsistemas.domain.Matricula;
import br.com.virtualsistemas.service.dto.MatriculaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring", uses = {})
public interface MatriculaMapper {


    @Mapping(source= "alunoId", target = "aluno.id")
    @Mapping(source= "disciplinaId", target = "disciplina.id")
    Matricula toEntity(MatriculaDTO dto);

    @Mapping( source= "aluno.id", target= "alunoId")
    @Mapping( source= "disciplina.id", target= "disciplinaId")
    MatriculaDTO toDto(Matricula entity);

}
