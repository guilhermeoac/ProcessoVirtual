package br.com.virtualsistemas.repository;

import br.com.virtualsistemas.domain.Disciplina;
import br.com.virtualsistemas.service.dto.DisciplinaDTO;
import br.com.virtualsistemas.service.dto.DropDownDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplinaRepository extends JpaRepository<Disciplina, Long> {
    @Query("select new br.com.virtualsistemas.service.dto.DisciplinaDTO(d.id, d.nome, d.limiteAlunos, d.cargaHoraria) " +
            "from Disciplina d " +
            "where (:#{#filtro.nome} IS NULL OR d.nome LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.nome}), '%')))" +
            "AND (:#{#filtro.limiteAlunos} IS NULL OR d.limiteAlunos = :#{#filtro.limiteAlunos})" +
            "AND (:#{#filtro.cargaHoraria} IS NULL OR d.cargaHoraria = :#{#filtro.cargaHoraria})")
    Page<DisciplinaDTO> listarDisciplinas(@Param("filtro") DisciplinaDTO filtro, Pageable pageable);

    @Query("select new br.com.virtualsistemas.service.dto.DropDownDTO(d.id, d.nome) " +
            "from Disciplina d")
    List<DropDownDTO> getAllDisciplinaDropDown();

    @Query("select d.limiteAlunos from Disciplina d where d.id = :id")
    Long verificaLimite(@Param("id") Long id);
}
