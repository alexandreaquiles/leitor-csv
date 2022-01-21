package br.com.alexandreaquiles.csv;

public class CsvException extends RuntimeException {
   CsvException(String mensagem, Throwable ex) {
        super(mensagem, ex);
   }

    public CsvException(String mensagem) {
        super(mensagem);
    }
}
