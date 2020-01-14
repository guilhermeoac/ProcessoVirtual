package br.com.virtualsistemas.repository;

import br.com.virtualsistemas.domain.Matricula;
import br.com.virtualsistemas.service.dto.MatriculaFiltroDTO;
import br.com.virtualsistemas.service.dto.MatriculaListDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MatriculaRepository extends JpaRepository<Matricula, Long> {
    @Query("select new br.com.virtualsistemas.service.dto.MatriculaListDTO(count(m.aluno), m.disciplina.id, m.disciplina.nome) " +
            "from Matricula m " +
            "where (:#{#filtro.dataInicio} IS NULL OR TRUNC(m.dataRegistro) > TO_DATE(:#{#filtro.dataInicioFormatada}, 'DD/MM/YYYY')) " +
            "AND (:#{#filtro.dataRegistro} IS NULL OR TRUNC(m.dataRegistro) = TO_DATE(:#{#filtro.dataRegistroFormatada}, 'DD/MM/YYYY')) " +
            "AND (:#{#filtro.dataFim} IS NULL OR TRUNC(m.dataRegistro) < TO_DATE(:#{#filtro.dataFimFormatada}, 'DD/MM/YYYY')) " +
            "AND (:#{#filtro.nomeDisciplina} IS NULL OR m.disciplina.nome LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.nomeDisciplina}), '%'))) " +
            "group by m.disciplina.id, m.disciplina.nome ")
    Page<MatriculaListDTO> listarMatriculas(@Param("filtro") MatriculaFiltroDTO filtro, Pageable pageable);

    @Query("select count(m.aluno) " +
            "from Matricula m " +
            "where m.disciplina.id = :id " )
    Long verificaLimiteAlunos(@Param("id")Long id);
}
