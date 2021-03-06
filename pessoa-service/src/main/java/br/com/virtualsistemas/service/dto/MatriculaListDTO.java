package br.com.virtualsistemas.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaListDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long numeroAlunos;

    private Long  disciplinaId;

    private String nomeDisciplina;
}
