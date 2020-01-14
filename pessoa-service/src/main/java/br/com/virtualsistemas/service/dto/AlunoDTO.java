package br.com.virtualsistemas.service.dto;

import br.com.virtualsistemas.enumeration.SexoEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String  nome;

    private SexoEnum sexo;

    private String  email;

    private String telefone;

    private String cpf;
}
