package br.com.alexandreaquiles.curso;

import br.com.alexandreaquiles.csv.LeitorCsv;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
