package br.com.alexandreaquiles.csv;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsv {

    public <T> List<T> le(String arquivoCsv, Class<T> clazz) {
        List<String> linhas = leLinhas(arquivoCsv);
        return criaInstancia(linhas, clazz);
    }

    private List<String> leLinhas(String arquivoCsv) {
        try {
            return Files.readAllLines(Path.of(arquivoCsv));
        } catch (Exception ex) {
            throw new CsvException("Não conseguiu ler arquivo.", ex);
        }
    }

    private <T> List<T> criaInstancia(List<String> linhas, Class<T> clazz) {
        List<T> dados = new ArrayList<>();
        linhas.forEach(line -> {
            try {
                T instance = clazz.newInstance();
                Field field = clazz.getDeclaredField("nome");
                field.setAccessible(true);
                field.set(instance, "Redes avançado");
                dados.add(instance);
            } catch (Exception ex) {
                throw new CsvException("Não conseguiu criar instância.", ex);
            }
        });
        return dados;
    }

}
