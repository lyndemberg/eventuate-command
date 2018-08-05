package com.weba.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class ArquivoFilme {

    @Id
    @GeneratedValue
    private Long id;
    private String nome;
    private String formato;
    private Long tamanho;

    @Override
    public String toString() {
        return "ArquivoFilme{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", formato='" + formato + '\'' +
                ", tamanho=" + tamanho +
                '}';
    }
}
