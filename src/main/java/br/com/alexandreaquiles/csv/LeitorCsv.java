package br.com.alexandreaquiles.csv;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class LeitorCsv {
    public <T> List<T> le(String arquivoCsv, Class<T> clazz) {
        ArrayList<T> dados = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Path.of(arquivoCsv));
            lines.forEach(line -> {
                try {
                    dados.add(clazz.newInstance());
                } catch (Exception ex) {
                    throw new CsvException("Não conseguiu criar instância.", ex);
                }
            });
            return dados;
        } catch (Exception ex) {
            throw new CsvException("Não conseguiu ler arquivo.", ex);
        }
    }
}
