package br.com.virtualsistemas.repository;

import br.com.virtualsistemas.domain.Aluno;
import br.com.virtualsistemas.service.dto.AlunoDTO;
import br.com.virtualsistemas.service.dto.DropDownDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("select new br.com.virtualsistemas.service.dto.AlunoDTO(a.id, a.nome, a.sexo, a.email, a.telefone, a.cpf) " +
            "from Aluno a " +
            "where (:#{#filtro.email} IS NULL OR LOWER( a.email) LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.email}), '%'))) " +
            "AND (:#{#filtro.telefone} IS NULL OR LOWER(a.telefone) LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.telefone}), '%'))) " +
            "AND (:#{#filtro.cpf} IS NULL OR a.cpf LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.cpf}), '%'))) " +
            "AND (:#{#filtro.nome} IS NULL OR a.nome LIKE LOWER(CONCAT(CONCAT('%', :#{#filtro.nome}), '%'))) " +
            "AND (:#{#filtro.sexo} IS NULL OR a.sexo = :#{#filtro.sexo})")
    Page<AlunoDTO> listarAlunos(@Param("filtro")AlunoDTO filtro, Pageable pageable);

    @Query("select new br.com.virtualsistemas.service.dto.DropDownDTO(a.id, a.nome) " +
            "from Aluno a")
    List<DropDownDTO> getAllAlunoDropDown();
}
