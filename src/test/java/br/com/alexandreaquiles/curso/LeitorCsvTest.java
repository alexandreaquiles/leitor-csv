package br.com.alexandreaquiles.curso;

import br.com.alexandreaquiles.csv.CsvException;
import br.com.alexandreaquiles.csv.LeitorCsv;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LeitorCsvTest {

    /*
        TODO
        criar teste do atributo quando a classe não é suportada
        criar teste com record
        duas versões do le: recebe string do arquivo e uma que recebe um InputStream
     */

    @Test
    void le__deve_extrair_cursos_corretamente() {
        var leitorCsv = new LeitorCsv();

        String arquivoCsv = Paths.get("src/test/resources/cursos.csv").toFile().getAbsolutePath();
        List<Curso> cursos = leitorCsv.le(arquivoCsv, Curso.class);

        assertEquals(3, cursos.size());

        Curso primeiroCurso = cursos.get(0);
        assertEquals(Curso.class, primeiroCurso.getClass());
        assertEquals("Redes avançado", primeiroCurso.getNome());
        assertEquals(6, primeiroCurso.getCargaHoraria());

        Curso segundoCurso = cursos.get(1);
        assertEquals(Curso.class, segundoCurso.getClass());
        assertEquals("Java e OO", segundoCurso.getNome());
        assertEquals(8, segundoCurso.getCargaHoraria());

        Curso terceiroCurso = cursos.get(2);
        assertEquals(Curso.class, terceiroCurso.getClass());
        assertEquals("Python básico", terceiroCurso.getNome());
        assertEquals(6, terceiroCurso.getCargaHoraria());
    }

    @Test
    void le__deve_lancar_excecao_se_a_classe_nao_pode_ser_instanciada() {
        CsvException csvException = assertThrows(CsvException.class, () -> {
            var leitorCsv = new LeitorCsv();
            String arquivoCsv = Paths.get("src/test/resources/cursos.csv").toFile().getAbsolutePath();
            Class<List> algoQueNaoPodeSerInstanciado = List.class;
            leitorCsv.le(arquivoCsv, algoQueNaoPodeSerInstanciado);
        });
        assertEquals("Não conseguiu criar instância.", csvException.getMessage());
    }

    @Test
    void le__deve_lancar_excecao_se_arquivo_nao_existe() {
        CsvException csvException = assertThrows(CsvException.class, () -> {
            var leitorCsv = new LeitorCsv();
            leitorCsv.le("arquivo-que-nao-existe", Curso.class);
        });
        assertEquals("Não conseguiu ler arquivo.", csvException.getMessage());
    }
}
