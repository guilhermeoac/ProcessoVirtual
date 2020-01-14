package br.com.virtualsistemas.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Table(name="TB_MATRICULA")
@Entity
@Getter
@Setter
public class Matricula implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_matricula")
	@SequenceGenerator(name = "sq_matricula", sequenceName = "sq_matricula", allocationSize = 1 )
	@Column(name="id_matricula")
	private Long id;

	@ManyToOne
	@JoinColumn(referencedColumnName = "ID_ALUNO", name = "ID_ALUNO", nullable = false)
	private Aluno  aluno;

	@ManyToOne
	@JoinColumn(referencedColumnName = "ID_DISCIPLINA", name = "ID_DISCIPLINA", nullable = false)
	private Disciplina disciplina;

	@Column(name="DH_REGISTRO", nullable = false)
	private Timestamp dataRegistro;



}
