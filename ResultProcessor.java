import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ResultProcessor {
    private ScoringStrategy strategy;

    public ResultProcessor(ScoringStrategy strategy) {
        this.strategy = strategy;
    }

    public void processarResultados(Disciplina disciplina, String gabarito) {
        List<String> respostas = disciplina.getRespostasAlunos();
        List<Aluno> resultados = new ArrayList<>();

        for (String resposta : respostas) {
            String[] partes = resposta.split("\t");
            if (partes.length != 2) {
                System.err.println("Formato de resposta inválido: " + resposta);
                continue;
            }
            String respostasAluno = partes[0].trim();
            String nomeAluno = partes[1].trim();

            int nota = strategy.calcularNota(respostasAluno, gabarito);
            resultados.add(new Aluno(nomeAluno, nota));
        }

        double media = calcularMedia(resultados);

        resultados.sort(Comparator.comparing(Aluno::getNome));
        FileFactory.escreverResultados(disciplina.getNome() + "_resultados_alfabetica", resultados, media);

        resultados.sort(Comparator.comparingInt(Aluno::getNota).reversed());
        FileFactory.escreverResultados(disciplina.getNome() + "_resultados_notas", resultados, media);
    }

    private double calcularMedia(List<Aluno> resultados) {
        if (resultados.isEmpty()) {
            return 0.0;
        }

        int soma = 0;

        for (Aluno resultado : resultados) {
            soma += resultado.getNota();
        }

        return (double) soma / resultados.size();
    }
}
