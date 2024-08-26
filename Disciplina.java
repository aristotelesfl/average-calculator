import java.util.ArrayList;
import java.util.List;

public class Disciplina {
    private String nome;
    private String gabarito;
    private List<String> respostasAlunos;

    public Disciplina(String nome, String gabarito) {
        this.nome = nome;
        this.gabarito = gabarito;
        this.respostasAlunos = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public String getGabarito() {
        return gabarito;
    }

    public void adicionarRespostas(String respostas) {
        respostasAlunos.add(respostas);
    }

    public List<String> getRespostasAlunos() {
        return respostasAlunos;
    }
}
