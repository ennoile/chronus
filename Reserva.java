public class Reserva {
    private String finalidade;
    private Usuario usuario;
    private Recurso recurso;
    private Alocacao alocacao;

    public Reserva(String finalidade, Usuario usuario, Recurso recurso, Alocacao alocacao){
        this.finalidade = finalidade;
        this.usuario = usuario;
        this.recurso = recurso;
        this.alocacao = alocacao; 
    }

    public void exibirDetalhes(){
        System.out.println("Reserva para: " + finalidade);
        System.out.println("Usuario: " + usuario.login);
        System.out.println("Recurso: " + recurso.getNome());
        System.out.println("Data: " + alocacao.getData());
        System.out.println("Hora Inicio: " + alocacao.getHoraInicio());
        System.out.println("Hora Fim: " + alocacao.getHoraFim());
    }

    public String getFinalidade(){
        return finalidade;
    }

    public Usuario getUsuario(){
        return usuario;
    }

    public Recurso getRecurso(){
        return recurso;
    }

    public Alocacao getAlocacao(){
        return alocacao;
    }
}
