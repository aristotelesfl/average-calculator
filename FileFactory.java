import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileFactory {
    public static void criarArquivoDisciplina(Disciplina disciplina) {
        String nomeDisciplina = disciplina.getNome();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDisciplina + "_respostas.txt"))) {
            for (String resposta : disciplina.getRespostasAlunos()) {
                writer.write(resposta);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo de respostas da disciplina " + nomeDisciplina);
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeDisciplina + "_gabarito.txt"))) {
            writer.write(disciplina.getGabarito());
        } catch (IOException e) {
            System.err.println("Erro ao criar arquivo de gabarito da disciplina " + nomeDisciplina);
            e.printStackTrace();
        }
    }

    public static void escreverResultados(String nomeArquivo, List<Aluno> resultados, Double media) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nomeArquivo + ".txt"))) {
            for (Aluno resultado : resultados) {
                writer.write(resultado.getNome() + ": " + resultado.getNota());
                writer.newLine();
            }

            if (media != null) {
                writer.write(String.format("Média da turma: %.2f", media));
            }
        } catch (IOException e) {
            System.err.println("Erro ao escrever resultados no arquivo " + nomeArquivo);
            e.printStackTrace();
        }
    }
}
