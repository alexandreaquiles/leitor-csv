package br.com.alexandreaquiles.curso;

import br.com.alexandreaquiles.csv.CsvException;
import br.com.alexandreaquiles.csv.LeitorCsv;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LeitorCsvTest {

    @Test
    void le__deve_extrair_cursos_corretamente() {
        var leitorCsv = new LeitorCsv();

        String arquivoCsv = Paths.get("src/test/resources/cursos.csv").toFile().getAbsolutePath();
        List<Curso> cursos = leitorCsv.le(arquivoCsv, Curso.class);

        assertEquals(3, cursos.size());

        assertEquals(Curso.class, cursos.get(0).getClass());
        assertEquals("Redes avançado", cursos.get(0).getNome());

        assertEquals(Curso.class, cursos.get(1).getClass());
        assertEquals(Curso.class, cursos.get(2).getClass());
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
