package br.com.virtualsistemas.service;

import br.com.virtualsistemas.service.dto.AlunoDTO;
import br.com.virtualsistemas.service.dto.DropDownDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlunoService {


    Page<AlunoDTO> listarAlunos(AlunoDTO alunoDTO, Pageable pageable);

    AlunoDTO save(AlunoDTO alunoDTO);

    AlunoDTO findOne(Long id);

    List<DropDownDTO> getAllDropDown();

    void excluir(Long id);

}
