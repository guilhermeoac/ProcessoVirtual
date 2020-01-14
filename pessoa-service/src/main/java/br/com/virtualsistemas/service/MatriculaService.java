package br.com.virtualsistemas.service;

import br.com.virtualsistemas.service.dto.MatriculaDTO;
import br.com.virtualsistemas.service.dto.MatriculaFiltroDTO;
import br.com.virtualsistemas.service.dto.MatriculaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MatriculaService {


    Page<MatriculaListDTO> listarMatriculas(MatriculaFiltroDTO matriculaFiltroDTO, Pageable pageable);

    MatriculaDTO save(MatriculaDTO matriculaDTO) throws Exception;

    MatriculaDTO findOne(Long id);

    void excluir(Long id);

}
