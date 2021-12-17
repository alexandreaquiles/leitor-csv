import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {

        // pegar as anotacoes dos atributos da classe br.com.alexandreaquiles.curso.Curso

        /*

        Map<String, Integer> cursoClassCsvPositionsByField = new HashMap<>();

        Class<Curso> cursoClass = Curso.class;
        Field[] cursoClassFields = cursoClass.getDeclaredFields();
        for (Field cursoClassField : cursoClassFields) {
            CsvColumn cursoClassFieldAnnotation = cursoClassField.getAnnotation(CsvColumn.class);
            if (cursoClassFieldAnnotation != null) {
                int positionCursoClassFieldAnnotation = cursoClassFieldAnnotation.position();
                cursoClassCsvPositionsByField.put(cursoClassField.getName(), positionCursoClassFieldAnnotation);
            }
        }

        System.out.println(cursoClassCsvPositionsByField);

        List<String> lines = Files.readAllLines(Path.of("./cursos.csv"));
        lines.stream().map(line -> {
            String[] chunks = line.split(";");

            Curso curso = new Curso();

            Integer nomeFieldPosition = cursoClassCsvPositionsByField.get("nome");
            curso.setNome(chunks[nomeFieldPosition]);

            Integer cargaHorariaFieldPosition = cursoClassCsvPositionsByField.get("cargaHoraria");
            curso.setCargaHoraria(Integer.valueOf(chunks[cargaHorariaFieldPosition]));

            return curso;
        }).forEach(System.out::println);


         */
    }
}
