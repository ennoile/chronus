import java.time.LocalTime;
import java.util.List;

public class Reserva {
    private String finalidade;
    private Alocacao alocacao;
    private Recurso recursos;

    public Reserva(String finalidade, Alocacao alocacao, Recurso recursos) {
        this.finalidade = finalidade;
        this.alocacao = alocacao;
        this.recursos = recursos;
    }

    public String getFinalidade() {
        return finalidade;
    }

    public void setFinalidade(String finalidade) {
        this.finalidade = finalidade;
    }

    public Alocacao getAlocacao() {
        return alocacao;
    }

    public void setAlocacao(Alocacao alocacao) {
        this.alocacao = alocacao;
    }

    public Recurso getRecursos() {
        return recursos;
    }

    public void setRecursos(Recurso recursos) {
        this.recursos = recursos;
    }

    public boolean marcarReserva(List<Reserva> reservas) {
        for (Reserva reserva : reservas) {
            if (reserva.getRecursos().equals(this.recursos) &&
                    reserva.getAlocacao().getData().equals(this.alocacao.getData())) {

                LocalTime inicioExistente = reserva.getAlocacao().getHoraInicial();
                LocalTime fimExistente = reserva.getAlocacao().getHoraFinal();

                LocalTime novoInicio = this.alocacao.getHoraInicial();
                LocalTime novoFim = this.alocacao.getHoraFinal();

                if (!(novoFim.isBefore(inicioExistente) || novoInicio.isAfter(fimExistente))) {
                    return false;
                }
            }
        }
        reservas.add(this);
        return true;
    }
}
