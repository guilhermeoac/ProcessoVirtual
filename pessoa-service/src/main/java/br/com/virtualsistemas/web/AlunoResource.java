package br.com.virtualsistemas.web;

import br.com.virtualsistemas.service.AlunoService;
import br.com.virtualsistemas.service.dto.AlunoDTO;
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
@RequestMapping("/api/aluno")
public class AlunoResource {


    private final AlunoService alunoService;

    @PostMapping
    @Timed(millis = 600000)
    public ResponseEntity<AlunoDTO> createAluno(@RequestBody AlunoDTO alunoDTO) throws URISyntaxException {
        log.debug("REST request to save Aluno : {}", alunoDTO);

        AlunoDTO result = alunoService.save(alunoDTO);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/filter")
    @Timed(millis = 600000)
    public ResponseEntity<Page<AlunoDTO>> getAluno(@RequestBody AlunoDTO alunoDTO, Pageable pageable){
        log.debug("REST request to get a page of Aluno");
        Page<AlunoDTO> declaracoesUtilizacao = alunoService.listarAlunos(alunoDTO, pageable);
        return new ResponseEntity<>(declaracoesUtilizacao, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Timed(millis = 600000)
    public ResponseEntity<AlunoDTO> getAlunoById(@PathVariable Long id) {
        log.debug("REST request to get Aluno : {}", id);
        AlunoDTO alunoDTO = alunoService.findOne(id);
        return ResponseEntity.ok().body(alunoDTO);
    }

    @GetMapping("")
    @Timed(millis = 600000)
    public ResponseEntity<List<DropDownDTO>> getAllAlunoDropDown() {
        log.debug("REST request to get DropDown Aluno : {}");
        List<DropDownDTO> dropDownDTO = alunoService.getAllDropDown();
        return ResponseEntity.ok().body(dropDownDTO);
    }

    @DeleteMapping("/{id}")
    @Timed(millis = 600000)
    public ResponseEntity excluir(@PathVariable Long id) {
        log.debug("REST request inactivate : {}", id);
        alunoService.excluir(id);
        return ResponseEntity.ok().build();
    }

}
