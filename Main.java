import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, Disciplina> disciplinas = new HashMap<>();

        System.out.println("=== Sistema de Gestão de Notas ===");

        while (true) {
            System.out.print("\nDeseja criar uma nova disciplina? (s/n): ");
            String resposta = scanner.nextLine().trim();

            if (resposta.equalsIgnoreCase("n")) {
                break;
            } else if (!resposta.equalsIgnoreCase("s")) {
                System.out.println("Opção inválida. Tente novamente.");
                continue;
            }

            System.out.print("Informe o nome da disciplina: ");
            String nomeDisciplina = scanner.nextLine().trim();

            if (disciplinas.containsKey(nomeDisciplina)) {
                System.out.println("A disciplina já existe. Tente outro nome.");
                continue;
            }

            String gabarito;
            while (true) {
                System.out.print("Informe o gabarito da disciplina (10 respostas com V ou F): ");
                gabarito = scanner.nextLine().trim().toUpperCase();
                if (gabarito.matches("[VF]{10}")) {
                    break;
                } else {
                    System.out.println("Gabarito inválido. Certifique-se de inserir exatamente 10 caracteres contendo apenas 'V' ou 'F'.");
                }
            }

            Disciplina disciplina = new Disciplina(nomeDisciplina, gabarito);

            while (true) {
                System.out.print("\nDeseja adicionar um aluno à disciplina " + nomeDisciplina + "? (s/n): ");
                String respostaAluno = scanner.nextLine().trim();

                if (respostaAluno.equalsIgnoreCase("n")) {
                    break;
                } else if (!respostaAluno.equalsIgnoreCase("s")) {
                    System.out.println("Opção inválida. Tente novamente.");
                    continue;
                }

                String respostasAluno;
                while (true) {
                    System.out.print("Informe as respostas do aluno (10 respostas com V ou F): ");
                    respostasAluno = scanner.nextLine().trim().toUpperCase();
                    if (respostasAluno.matches("[VF]{10}")) {
                        break;
                    } else {
                        System.out.println("Respostas inválidas. Certifique-se de inserir exatamente 10 caracteres contendo apenas 'V' ou 'F'.");
                    }
                }

                System.out.print("Informe o nome do aluno: ");
                String nomeAluno = scanner.nextLine().trim();

                disciplina.adicionarRespostas(respostasAluno + "\t" + nomeAluno);
                System.out.println("Aluno adicionado com sucesso.");
            }

            FileFactory.criarArquivoDisciplina(disciplina);
            disciplinas.put(nomeDisciplina, disciplina);
            System.out.println("Disciplina '" + nomeDisciplina + "' criada com sucesso.");
        }

        while (true) {
            System.out.println("\n=== Menu Principal ===");
            System.out.println("1. Calcular notas de uma disciplina");
            System.out.println("2. Listar disciplinas disponíveis");
            System.out.println("3. Sair");

            System.out.print("Escolha uma opção: ");
            String opcao = scanner.nextLine().trim();

            switch (opcao) {
                case "1":
                    if (disciplinas.isEmpty()) {
                        System.out.println("Nenhuma disciplina cadastrada. Crie uma disciplina primeiro.");
                        continue;
                    }

                    System.out.print("Informe o nome da disciplina para calcular as notas: ");
                    String nomeDisciplina = scanner.nextLine().trim();

                    Disciplina disciplina = disciplinas.get(nomeDisciplina);
                    if (disciplina == null) {
                        System.out.println("Disciplina não encontrada.");
                        continue;
                    }

                    ResultProcessor processor = new ResultProcessor(new DefaultScoringStrategy());
                    processor.processarResultados(disciplina);

                    System.out.println("Notas calculadas e arquivos gerados com sucesso:");
                    System.out.println("- " + nomeDisciplina + "_resultados_alfabetica.txt");
                    System.out.println("- " + nomeDisciplina + "_resultados_notas.txt");
                    break;

                case "2":
                    if (disciplinas.isEmpty()) {
                        System.out.println("Nenhuma disciplina cadastrada.");
                    } else {
                        System.out.println("Disciplinas cadastradas:");
                        for (String nome : disciplinas.keySet()) {
                            System.out.println("- " + nome);
                        }
                    }
                    break;

                case "3":
                    System.out.println("Encerrando o programa. Até mais!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }
    }
}
