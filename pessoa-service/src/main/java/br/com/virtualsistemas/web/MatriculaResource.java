package br.com.virtualsistemas.web;

import br.com.virtualsistemas.service.MatriculaService;
import br.com.virtualsistemas.service.dto.MatriculaDTO;
import br.com.virtualsistemas.service.dto.MatriculaFiltroDTO;
import br.com.virtualsistemas.service.dto.MatriculaListDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Timed;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/matricula")

public class MatriculaResource {


    private final MatriculaService matriculaService;

    @PostMapping
    @Timed(millis = 600000)
    public ResponseEntity<MatriculaDTO> createMatricula(@RequestBody MatriculaDTO matriculaDTO) throws Exception {
        log.debug("REST request to save Matricula : {}", matriculaDTO);

        MatriculaDTO result = matriculaService.save(matriculaDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/filter")
    @Timed(millis = 600000)
    public ResponseEntity<Page<MatriculaListDTO>> getMatricula(@RequestBody MatriculaFiltroDTO matriculaFiltroDTO, Pageable pageable){
        log.debug("REST request to get a page of Matricula");
        Page<MatriculaListDTO> declaracoesUtilizacao = matriculaService.listarMatriculas(matriculaFiltroDTO, pageable);
        return new ResponseEntity<>(declaracoesUtilizacao, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Timed(millis = 600000)
    public ResponseEntity<MatriculaDTO> getMatriculaById(@PathVariable Long id) {
        log.debug("REST request to get Matricula : {}", id);
        MatriculaDTO matriculaDTO = matriculaService.findOne(id);
        return ResponseEntity.ok().body(matriculaDTO);
    }

    @DeleteMapping("/{id}")
    @Timed(millis = 600000)
    public ResponseEntity excluir(@PathVariable Long id) {
        log.debug("REST request inactivate : {}", id);
        matriculaService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
