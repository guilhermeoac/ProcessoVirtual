package br.com.virtualsistemas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="TB_DISCIPLINA")
@Entity
@Getter
@Setter
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_disciplina")
	@SequenceGenerator(name = "sq_disciplina", sequenceName = "sq_disciplina", allocationSize = 1 )
	@Column(name="id_disciplina")
	private Long id;

	@Column(name="ds_nome")
	private String  nome;

	@Column(name="nu_limite_alunos")
	private Long limiteAlunos;

	@Column(name="nu_carga_horaria")
	private Long  cargaHoraria;



}
