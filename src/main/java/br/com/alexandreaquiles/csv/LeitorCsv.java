package br.com.alexandreaquiles.csv;

import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class LeitorCsv {

    public <T> List<T> le(String arquivoCsv, Class<T> clazz) {
        List<String> linhas = leLinhas(arquivoCsv);
        return criaInstancias(linhas, clazz);
    }

    private List<String> leLinhas(String arquivoCsv) {
        try {
            return Files.readAllLines(Path.of(arquivoCsv));
        } catch (Exception ex) {
            throw new CsvException("Não conseguiu ler arquivo.", ex);
        }
    }

    private <T> List<T> criaInstancias(List<String> linhas, Class<T> clazz) {
        return linhas.stream().map(linha -> criaInstancia(linha, clazz)).toList();
    }

    private <T> T criaInstancia(String linha, Class<T> clazz) {
        try {
            T instancia = clazz.newInstance();

            Field[] atributos = clazz.getDeclaredFields();
            String[] dados = linha.split(";");

            Arrays.stream(atributos).forEach(atributo -> {
                CsvColumn anotacao = atributo.getAnnotation(CsvColumn.class);
                int posicao = anotacao.position();
                String dado = dados[posicao];

                atributo.setAccessible(true);
                try {
                    Class<?> classeAtributo = atributo.getType();
                    if (classeAtributo.equals(String.class)) atributo.set(instancia, dado);
                    else if (classeAtributo.equals(Integer.class)) atributo.set(instancia, Integer.valueOf(dado));
                    else
                        throw new CsvException("A %s não é suportada para o atributo %s".formatted(classeAtributo, atributo));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
            return instancia;
        } catch (CsvException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CsvException("Não conseguiu criar instância.", ex);
        }
    }
}
