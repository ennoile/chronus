import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Recurso {
    private String nome;
    private String tipo;
    private boolean interditado;
    private Usuario gerenteResponsavel;

    public Recurso(String nome, String tipo, Usuario gerenteResponsavel) {
        this.nome = nome;
        this.tipo = tipo;
        this.interditado = false;
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isInterditado() {
        return interditado;
    }

    public Usuario getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void interditar() {
        this.interditado = true;
    }

    public void desinterditar() {
        this.interditado = false;
    }

    public void alterarGerente(Gerente novoGerente) {
        this.gerenteResponsavel = novoGerente;
    }

    public void cadastrarRecurso(List<Recurso> recursos) {
        recursos.add(this);
    }

    public void listarHorariosLivres(List<Reserva> reservas, LocalDate data) {
        List<LocalTime> horariosLivres = new ArrayList<>();

        for (int hora = 8; hora < 22; hora++) {
            horariosLivres.add(LocalTime.of(hora, 0));
        }

        for (Reserva reserva : reservas) {
            if (reserva.getRecursos().equals(this) &&
                    reserva.getAlocacao().getData().equals(data)) {

                LocalTime inicio = reserva.getAlocacao().getHoraInicial();
                LocalTime fim = reserva.getAlocacao().getHoraFinal();

                horariosLivres.removeIf(hora -> !hora.isBefore(inicio) && hora.isBefore(fim));
            }
        }

        System.out.println("\nHorários livres para o recurso \"" + this.getNome() + "\" em " + data + ":");
        if (horariosLivres.isEmpty()) {
            System.out.println("Nenhum horário livre disponível.");
        } else {
            for (LocalTime hora : horariosLivres) {
                System.out.println("- " + hora + " às " + hora.plusHours(1));
            }
        }
        System.out.println();
    }
}
