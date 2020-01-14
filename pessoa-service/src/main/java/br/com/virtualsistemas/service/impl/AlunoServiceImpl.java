package br.com.virtualsistemas.service.impl;

import br.com.virtualsistemas.domain.Aluno;
import br.com.virtualsistemas.repository.AlunoRepository;
import br.com.virtualsistemas.service.AlunoService;
import br.com.virtualsistemas.service.dto.AlunoDTO;
import br.com.virtualsistemas.service.dto.DropDownDTO;
import br.com.virtualsistemas.service.mapper.AlunoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;
    private final AlunoMapper alunoMapper;

    @Override
    public AlunoDTO save(AlunoDTO alunoDTO) {
        Aluno aluno = alunoMapper.toEntity(alunoDTO);
        return alunoMapper.toDto(alunoRepository.save(aluno));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<AlunoDTO> listarAlunos(AlunoDTO alunoDTO, Pageable pageable) {
        return alunoRepository.listarAlunos(alunoDTO,pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public AlunoDTO findOne(Long id) {
        return alunoMapper.toDto(alunoRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DropDownDTO> getAllDropDown() {
        return alunoRepository.getAllAlunoDropDown();
    }

    @Override
    public void excluir(Long id) {
            alunoRepository.delete(id);
    }
}
