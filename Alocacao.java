import java.time.LocalDate;
import java.time.LocalTime;

public class Alocacao {
    private LocalDate data;
    private LocalTime horaInicio;
    private LocalTime horaFim;

    public Alocacao(LocalDate data, LocalTime horaInicio, LocalTime horaFim){
        this.data = data;
        this.horaInicio = horaInicio;
        this.horaFim = horaFim;
    }

    public LocalDate getData(){
        return data;
    }

    public LocalTime getHoraInicio(){
        return horaInicio;
    }

    public LocalTime getHoraFim(){
        return horaFim;
    }

    public void exibirAlocacao(){
        System.out.println("Data: " + data);
        System.out.println("Hora Inicial: " + horaInicio);
        System.out.println("Hora Final: " + horaFim);
    }
}
