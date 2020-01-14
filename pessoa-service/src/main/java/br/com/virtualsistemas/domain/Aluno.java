package br.com.virtualsistemas.domain;

import br.com.virtualsistemas.enumeration.SexoEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.io.Serializable;

@Table(name="tb_aluno")
@Entity
@Getter
@Setter
public class Aluno implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sq_aluno")
	@SequenceGenerator(name = "sq_aluno", sequenceName = "sq_aluno", allocationSize = 1 )
	@Column(name="id_aluno")
	private Long id;
	
	@Column(name="ds_nome")
	private String  nome;
			
	@Column(name="ds_sexo")
	private SexoEnum sexo;

	@Column(name="ds_email")
	private String  email;

	@Column(name="ds_telefone")
	private String telefone;

	@Column(name="ds_cpf")
	private String cpf;

}
