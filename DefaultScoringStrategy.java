public class DefaultScoringStrategy implements ScoringStrategy {
    @Override
    public int calcularNota(String respostasAluno, String gabarito) {
        if (respostasAluno.equals("VVVVVVVVVV") || respostasAluno.equals("FFFFFFFFFF")) {
            return 0;
        }

        int acertos = 0;
        for (int i = 0; i < gabarito.length(); i++) {
            if (respostasAluno.charAt(i) == gabarito.charAt(i)) {
                acertos++;
            }
        }
        return acertos;
    }
}