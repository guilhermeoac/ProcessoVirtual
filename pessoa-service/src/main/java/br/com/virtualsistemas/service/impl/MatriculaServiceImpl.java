package br.com.virtualsistemas.service.impl;

import br.com.virtualsistemas.domain.Matricula;
import br.com.virtualsistemas.repository.MatriculaRepository;
import br.com.virtualsistemas.service.DisciplinaService;
import br.com.virtualsistemas.service.MatriculaService;
import br.com.virtualsistemas.service.dto.MatriculaDTO;
import br.com.virtualsistemas.service.dto.MatriculaFiltroDTO;
import br.com.virtualsistemas.service.dto.MatriculaListDTO;
import br.com.virtualsistemas.service.mapper.MatriculaMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaRepository matriculaRepository;
    private final MatriculaMapper matriculaMapper;
    private final DisciplinaService disciplinaService;

    @Override
    public MatriculaDTO save(MatriculaDTO matriculaDTO) throws Exception {
        verificaLimiteAlunos(matriculaDTO.getDisciplinaId());
        matriculaDTO.setDataRegistro(Timestamp.valueOf(LocalDateTime.now()));
        Matricula matricula = matriculaMapper.toEntity(matriculaDTO);
        return matriculaMapper.toDto(matriculaRepository.save(matricula));
    }

    @Transactional(readOnly = true)
    @Override
    public Page<MatriculaListDTO> listarMatriculas(MatriculaFiltroDTO matriculaFiltroDTO, Pageable pageable) {
        return matriculaRepository.listarMatriculas(matriculaFiltroDTO,pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public MatriculaDTO findOne(Long id) {
        return matriculaMapper.toDto(matriculaRepository.findOne(id));
    }

    @Override
    public void excluir(Long id) {
            matriculaRepository.delete(id);
    }

    private void verificaLimiteAlunos(Long id) throws Exception {
        if(matriculaRepository.verificaLimiteAlunos(id) >= disciplinaService.verificaLimite(id)){
            throw new Exception("Excedeu o limite de alunos");
        }
    }
}
