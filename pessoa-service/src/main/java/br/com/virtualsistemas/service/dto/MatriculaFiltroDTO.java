package br.com.virtualsistemas.service.dto;

import br.com.virtualsistemas.util.FormatUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatriculaFiltroDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String  nomeDisciplina;

    private Timestamp dataRegistro;

    private Timestamp dataInicio;

    private Timestamp dataFim;

    @JsonIgnore
    public String getDataInicioFormatada(){
        return FormatUtil.formataData(this.dataInicio);
    }

    @JsonIgnore
    public String getDataFimFormatada(){
        return FormatUtil.formataData(this.dataFim);
    }

    @JsonIgnore
    public String getDataRegistroFormatada(){
        return FormatUtil.formataData(this.dataRegistro);
    }
}
