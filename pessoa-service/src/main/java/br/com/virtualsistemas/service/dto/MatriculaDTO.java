package br.com.virtualsistemas.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long alunoId;

    private Long  disciplinaId;

    private Timestamp dataRegistro;
}
