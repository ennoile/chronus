import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Recurso> recursos = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();
    private static List<Reserva> reservas = new ArrayList<>();

    private static void inicializarDados() {
        Gerente gerente = new Gerente("admin", "admin", "admin", "admin@admin.com", "sala 0", "00", "0000-0000");
        usuarios.add(gerente);

        Recurso recurso1 = new Recurso("Sala de Reunião", "Sala", gerente);
        Recurso recurso2 = new Recurso("Projetor", "Equipamento", gerente);
        Recurso recurso3 = new Recurso("Computador", "Equipamento", gerente);
        recursos.add(recurso1);
        recursos.add(recurso2);
        recursos.add(recurso3);
    }

    public static void main(String[] args) {
        inicializarDados();

        int opcao = -1;

        do {
            menuPrincipal();
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarRecurso();
                    break;
                case 2:
                    listarRecursos();
                    break;
                case 3:
                    cadastrarUsuario();
                    break;
                case 4:
                    reservarRecurso();
                    break;
                case 5:
                    listarReservas();
                    break;
                case 6:
                    listarHorariosDisponiveis();
                    break;
                case 7:
                    gerenciarInterdicaoRecurso();
                    break;
                case 0:
                    System.out.println("\nEncerrando o programa...\n");
                    break;
                default:
                    break;
            }
        } while (opcao != 0);
    }

    public static void menuPrincipal() {
        System.out.println("-----Menu-Principal-----");
        System.out.println("1. Cadastrar Recurso");
        System.out.println("2. Listar Recursos");
        System.out.println("3. Cadastrar Usuário (Gerente)");
        System.out.println("4. Reservar Recurso");
        System.out.println("5. Listar Reservas");
        System.out.println("6. Listar Horários Disponíveis");
        System.out.println("7. Interditar/Desinterditar Recurso");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarRecurso() {
        System.out.print("\nDigite o nome do recurso: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o tipo do recurso: ");
        String tipo = scanner.nextLine();
        System.out.println("Gerentes disponíveis:");
        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            if (usuario instanceof Gerente) {
                System.out.println(" " + (i + 1) + ". " + usuario.getNome());
            }
        }
        System.out.print("Escolha o número do gerente responsável: ");
        int gerenteIndex = scanner.nextInt() - 1;
        scanner.nextLine();
        if (gerenteIndex < 0 || gerenteIndex >= usuarios.size()
                || !(usuarios.get(gerenteIndex) instanceof Gerente)) {
            System.out.println("Gerente inválido. Tente novamente.\n");
            return;
        }
        Recurso recurso = new Recurso(nome, tipo, (Gerente) usuarios.get(gerenteIndex));
        recurso.cadastrarRecurso(recursos);
        System.out.println("Recurso cadastrado com sucesso!\n");
    }

    private static void listarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("\nNenhum recurso cadastrado.");
            return;
        }
        System.out.println("\nRecursos cadastrados:");
        for (Recurso recurso : recursos) {
            System.out.println("Nome: " + recurso.getNome() + ", Tipo: " + recurso.getTipo()
                    + ", Gerente: " + recurso.getGerenteResponsavel().getNome()
                    + ", Interditado: " + recurso.isInterditado());
        }
        System.out.println("\n");
    }

    private static void cadastrarUsuario() {
        System.out.print("\nNome: ");
        String nome = scanner.nextLine();
        System.out.print("Tipo (Gerente/Administrador): ");
        String tipo = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Digite o login do usuário: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha do usuário: ");
        String senha = scanner.nextLine();
        System.out.print("Digite a sala do usuário: ");
        String sala = scanner.nextLine();
        System.out.print("Digite o ramal do usuário: ");
        String ramal = scanner.nextLine();
        System.out.print("Digite o telefone do usuário: ");
        String telefone = scanner.nextLine();

        Usuario usuario;
        if (tipo.equalsIgnoreCase("Gerente")) {
            usuario = new Gerente(nome, login, senha, email, sala, ramal, telefone);
        } else {
            usuario = new Administrador(nome, login, senha, email, sala, ramal, telefone);
        }
        usuario.cadastrarUsuario(usuarios);
        System.out.println("Usuário cadastrado com sucesso!\n");
    }

    private static void reservarRecurso() {
        List<Recurso> recursosDisponiveis = new ArrayList<>();

        System.out.println("\nRecursos disponíveis para reserva:");
        for (Recurso recurso : recursos) {
            if (!recurso.isInterditado()) {
                recursosDisponiveis.add(recurso);
            }
        }

        if (recursosDisponiveis.isEmpty()) {
            System.out.println("Nenhum recurso disponível para reserva.\n");
            return;
        }

        for (int i = 0; i < recursosDisponiveis.size(); i++) {
            Recurso recurso = recursosDisponiveis.get(i);
            System.out.println(" " + (i + 1) + ". " + recurso.getNome() + " (" + recurso.getTipo() + ")");
        }

        System.out.print("Escolha o número do recurso: ");
        int recursoIndex = scanner.nextInt() - 1;
        scanner.nextLine();

        if (recursoIndex < 0 || recursoIndex >= recursosDisponiveis.size()) {
            System.out.println("Recurso inválido. Tente novamente.\n");
            return;
        }

        Recurso recurso = recursosDisponiveis.get(recursoIndex);

        System.out.print("Digite a finalidade da reserva: ");
        String finalidade = scanner.nextLine();

        System.out.print("Digite a data da reserva (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();
        LocalDate data;
        try {
            data = LocalDate.parse(dataStr);
        } catch (Exception e) {
            System.out.println("Data inválida. Tente novamente.\n");
            return;
        }

        System.out.print("Digite o horário de início da reserva (HH:MM): ");
        String horaInicioStr = scanner.nextLine();
        LocalTime horaInicio;
        try {
            horaInicio = LocalTime.parse(horaInicioStr);
        } catch (Exception e) {
            System.out.println("Horário de início inválido. Tente novamente.\n");
            return;
        }

        System.out.print("Digite o horário de término da reserva (HH:MM): ");
        String horaFimStr = scanner.nextLine();
        LocalTime horaFim;
        try {
            horaFim = LocalTime.parse(horaFimStr);
        } catch (Exception e) {
            System.out.println("Horário de término inválido. Tente novamente.\n");
            return;
        }

        if (horaFim.isBefore(horaInicio)) {
            System.out.println("Horário de término não pode ser antes do horário de início. Tente novamente.\n");
            return;
        }

        Alocacao alocacao = new Alocacao(data, horaInicio, horaFim);
        Reserva reserva = new Reserva(finalidade, alocacao, recurso);

        if (reserva.reservarRecurso(reservas)) {
            System.out.println("Recurso reservado com sucesso!\n");
        } else {
            System.out.println(
                    "Falha ao reservar o recurso. Verifique se já existe uma reserva para este recurso no mesmo horário.\n");
        }

        System.out.print("\n");
    }

    private static void listarReservas() {
        if (reservas.isEmpty()) {
            System.out.println("\nNenhuma reserva cadastrada.");
            return;
        }
        System.out.println("\nReservas cadastradas:");
        for (Reserva reserva : reservas) {
            System.out
                    .println("Finalidade: " + reserva.getFinalidade() + ", Recurso: " + reserva.getRecursos().getNome()
                            + ", Data: " + reserva.getAlocacao().getData() + ", Horário: "
                            + reserva.getAlocacao().getHoraInicial() + " - " + reserva.getAlocacao().getHoraFinal());
        }
        System.out.println("\n");
    }

    private static void listarHorariosDisponiveis() {
        System.out.println("\nRecursos disponíveis:");
        for (int i = 0; i < recursos.size(); i++) {
            System.out.println((i + 1) + ". " + recursos.get(i).getNome());
        }

        System.out.print("Escolha o número do recurso: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= recursos.size()) {
            System.out.println("Recurso inválido.\n");
            return;
        }

        System.out.print("Digite a data (YYYY-MM-DD): ");
        String dataStr = scanner.nextLine();
        try {
            LocalDate data = LocalDate.parse(dataStr);
            recursos.get(index).listarHorariosLivres(reservas, data);
        } catch (Exception e) {
            System.out.println("Data inválida.\n");
        }
    }

    private static void gerenciarInterdicaoRecurso() {
        if (recursos.isEmpty()) {
            System.out.println("\nNenhum recurso cadastrado.\n");
            return;
        }

        System.out.println("\nRecursos disponíveis:");
        for (int i = 0; i < recursos.size(); i++) {
            Recurso recurso = recursos.get(i);
            System.out.println((i + 1) + ". " + recurso.getNome() + " - Interditado: " + recurso.isInterditado());
        }

        System.out.print("Escolha o número do recurso: ");
        int index = scanner.nextInt() - 1;
        scanner.nextLine();

        if (index < 0 || index >= recursos.size()) {
            System.out.println("Recurso inválido.\n");
            return;
        }

        Recurso recurso = recursos.get(index);

        if (recurso.isInterditado()) {
            System.out.print("Este recurso está interditado. Deseja desinterditar? (s/n): ");
            String opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("s")) {
                recurso.desinterditar();
                System.out.println("Recurso desinterditado com sucesso.\n");
            } else {
                System.out.println("Operação cancelada.\n");
            }
        } else {
            System.out.print("Este recurso está disponível. Deseja interditar? (s/n): ");
            String opcao = scanner.nextLine();
            if (opcao.equalsIgnoreCase("s")) {
                recurso.interditar();
                System.out.println("Recurso interditado com sucesso.\n");
            } else {
                System.out.println("Operação cancelada.\n");
            }
        }
    }
}
