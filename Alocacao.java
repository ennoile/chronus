import java.time.LocalDate;
import java.time.LocalTime;

public class Alocacao {
    private LocalDate data;
    private LocalTime horaInicial;
    private LocalTime horaFinal;

    public Alocacao(LocalDate data, LocalTime horaInicial, LocalTime horaFinal) {
        this.data = data;
        this.horaInicial = horaInicial;
        this.horaFinal = horaFinal;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public LocalTime getHoraInicial() {
        return horaInicial;
    }

    public void setHoraInicial(LocalTime horaInicial) {
        this.horaInicial = horaInicial;
    }

    public LocalTime getHoraFinal() {
        return horaFinal;
    }

    public void setHoraFinal(LocalTime horaFinal) {
        this.horaFinal = horaFinal;
    }
}
