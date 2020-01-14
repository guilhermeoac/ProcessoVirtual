package br.com.virtualsistemas.service;

import br.com.virtualsistemas.service.dto.DisciplinaDTO;
import br.com.virtualsistemas.service.dto.DropDownDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DisciplinaService {


    Page<DisciplinaDTO> listarDisciplinas(DisciplinaDTO disciplinaDTO, Pageable pageable);

    DisciplinaDTO save(DisciplinaDTO disciplinaDTO);

    DisciplinaDTO findOne(Long id);

    List<DropDownDTO> getAllDropDown();

    Long verificaLimite(Long id);

    void excluir(Long id);

}
