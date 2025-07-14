import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Reserva> reservas = new ArrayList<>();
    private static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {
        int opcao;

        do {
            mostrarMenuPrincipal();
            opcao = ler.nextInt();
            ler.nextLine();

            switch (opcao) {
                case 1:
                    menuReserva();
                    break;
                case 2:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }
        } while (opcao != 2);

        ler.close();
    }

    private static void mostrarMenuPrincipal() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Menu de Reservas");
        System.out.println("2. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void menuReserva() {
        int opcao;
        do {
            System.out.println("\n--- MENU DE RESERVAS ---");
            System.out.println("1. Criar reserva");
            System.out.println("2. Listar reservas");
            System.out.println("3. Cancelar reserva");
            System.out.println("4. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = ler.nextInt();
            ler.nextLine();

            switch (opcao) {
                case 1:
                    criarReserva();
                    break;
                case 2:
                    listarReservas();
                    break;
                case 3:
                    cancelarReserva();
                    break;
                case 4:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 4);
    }

    private static void criarReserva() {
        System.out.print("Login do Gerente: ");
        String loginGerente = ler.nextLine();

        System.out.print("Senha do Gerente: ");
        String senhaGerente = ler.nextLine();

        Gerente gerente = new Gerente(loginGerente, senhaGerente, "", "", "", "");

        System.out.print("Nome do Recurso: ");
        String nomeRecurso = ler.nextLine();

        System.out.print("Tipo do Recurso: ");
        String tipoRecurso = ler.nextLine();

        Recurso recurso = new Recurso(nomeRecurso, tipoRecurso, gerente, true);

        System.out.print("Login do Usuário: ");
        String loginUsuario = ler.nextLine();

        System.out.print("Senha do Usuário: ");
        String senhaUsuario = ler.nextLine();

        Usuario usuario = new Usuario(loginUsuario, senhaUsuario, "", "", "", "");

        System.out.print("Data da reserva (AAAA-MM-DD): ");
        LocalDate data = LocalDate.parse(ler.nextLine());

        System.out.print("Hora de início (HH:MM): ");
        LocalTime inicio = LocalTime.parse(ler.nextLine());

        System.out.print("Hora de término (HH:MM): ");
        LocalTime fim = LocalTime.parse(ler.nextLine());

        Alocacao alocacao = new Alocacao(data, inicio, fim);

        System.out.print("Finalidade da reserva: ");
        String finalidade = ler.nextLine();

        Reserva reserva = new Reserva(finalidade, usuario, recurso, alocacao);
        reservas.add(reserva);

        System.out.println("Reserva criada com sucesso!");
    }

    private static void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
        } else {
            System.out.println("\n=== LISTA DE RESERVAS ===");
            int i = 1;
            for (Reserva r : reservas) {
                System.out.println("Reserva " + i + ":");
                r.exibirDetalhes();
                i++;
            }
        }
    }

    private static void cancelarReserva() {
        listarReservas();
        if (!reservas.isEmpty()) {
            System.out.print("Digite o número da reserva que deseja cancelar: ");
            int index = ler.nextInt();
            ler.nextLine();
            if (index >= 1 && index <= reservas.size()) {
                reservas.remove(index - 1);
                System.out.println("Reserva cancelada com sucesso.");
            } else {
                System.out.println("Reserva inválida.");
            }
        }
    }
}
