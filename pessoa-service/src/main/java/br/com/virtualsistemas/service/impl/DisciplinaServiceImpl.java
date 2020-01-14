package br.com.virtualsistemas.service.impl;

import br.com.virtualsistemas.domain.Disciplina;
import br.com.virtualsistemas.repository.DisciplinaRepository;
import br.com.virtualsistemas.service.DisciplinaService;
import br.com.virtualsistemas.service.dto.DisciplinaDTO;
import br.com.virtualsistemas.service.dto.DropDownDTO;
import br.com.virtualsistemas.service.mapper.DisciplinaMapper;
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
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;
    private final DisciplinaMapper disciplinaMapper;

    @Override
    public DisciplinaDTO save(DisciplinaDTO disciplinaDTO) {
        Disciplina disciplina = disciplinaMapper.toEntity(disciplinaDTO);
        return disciplinaMapper.toDto(disciplinaRepository.save(disciplina));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DisciplinaDTO> listarDisciplinas(DisciplinaDTO disciplinaDTO, Pageable pageable) {
        return disciplinaRepository.listarDisciplinas(disciplinaDTO,pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public DisciplinaDTO findOne(Long id) {
        return disciplinaMapper.toDto(disciplinaRepository.findOne(id));
    }

    @Transactional(readOnly = true)
    @Override
    public List<DropDownDTO> getAllDropDown() {
        return disciplinaRepository.getAllDisciplinaDropDown();
    }

    @Override
    public Long verificaLimite(Long id) {
        return disciplinaRepository.verificaLimite(id);
    }

    @Override
    public void excluir(Long id) {
            disciplinaRepository.delete(id);
    }
}
