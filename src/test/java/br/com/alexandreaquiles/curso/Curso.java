package br.com.alexandreaquiles.curso;

import br.com.alexandreaquiles.csv.CsvColumn;

public class Curso {

    @CsvColumn(position = 0)
    private String nome;

    @CsvColumn(position = 1)
    private Integer cargaHoraria;

    public String getNome() {
        return nome;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    @Override
    public String toString() {
        return "br.com.alexandreaquiles.curso.Curso{" +
                "nome='" + nome + '\'' +
                ", cargaHoraria=" + cargaHoraria +
                '}';
    }
}
