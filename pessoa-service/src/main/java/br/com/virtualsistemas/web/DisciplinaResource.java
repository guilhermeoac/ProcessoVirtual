package br.com.virtualsistemas.web;

import br.com.virtualsistemas.service.DisciplinaService;
import br.com.virtualsistemas.service.dto.DisciplinaDTO;
import br.com.virtualsistemas.service.dto.DropDownDTO;
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
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/disciplina")

public class DisciplinaResource {


    private final DisciplinaService disciplinaService;

    @PostMapping
    @Timed(millis = 600000)
    public ResponseEntity<DisciplinaDTO> createDisciplina(@RequestBody DisciplinaDTO disciplinaDTO) throws URISyntaxException {
        log.debug("REST request to save Disciplina : {}", disciplinaDTO);

        DisciplinaDTO result = disciplinaService.save(disciplinaDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/filter")
    @Timed(millis = 600000)
    public ResponseEntity<Page<DisciplinaDTO>> getDisciplina(@RequestBody DisciplinaDTO disciplinaDTO, Pageable pageable){
        log.debug("REST request to get a page of Disciplina");
        Page<DisciplinaDTO> declaracoesUtilizacao = disciplinaService.listarDisciplinas(disciplinaDTO, pageable);
        return new ResponseEntity<>(declaracoesUtilizacao, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Timed(millis = 600000)
    public ResponseEntity<DisciplinaDTO> getDisciplinaById(@PathVariable Long id) {
        log.debug("REST request to get Disciplina : {}", id);
        DisciplinaDTO disciplinaDTO = disciplinaService.findOne(id);
        return ResponseEntity.ok().body(disciplinaDTO);
    }

    @GetMapping("")
    @Timed(millis = 600000)
    public ResponseEntity<List<DropDownDTO>> getAllDisciplinaDropDown() {
        log.debug("REST request to get DropDown Disciplina : {}");
        List<DropDownDTO> dropDownDTO = disciplinaService.getAllDropDown();
        return ResponseEntity.ok().body(dropDownDTO);
    }

    @DeleteMapping("/{id}")
    @Timed(millis = 600000)
    public ResponseEntity excluir(@PathVariable Long id) {
        log.debug("REST request inactivate : {}", id);
        disciplinaService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
