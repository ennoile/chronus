public class Recurso {
    private String nome;
    private String tipo;
    private boolean interditado; 
    private Gerente gerenteResponsavel;

    public Recurso (String nome, String tipo, Gerente gerenteResponsavel, Boolean interditado){
        this.nome = nome;
        this.tipo = tipo;
        this.interditado = false;
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public void interditar(){
        this.interditado = true;
    }

    public void desinterditar(){
        this.interditado = false;
    }

    public void consultar(){
        System.out.println("Nome: " + nome);
        System.out.println("Tipo: " + nome);
        System.out.println("Interditado: " + (interditado ? "Sim" : "Nao"));
        System.out.println("Gerente Responsavel: " + gerenteResponsavel.login);
    }

    public void alterarGerente(Gerente novoGerente){
        this.gerenteResponsavel = novoGerente;
    }

    public boolean isInterditado(){
        return interditado;
    }

    public String getNome(){
        return nome;
    }

    public String getTipo(){
        return tipo;
    }

    public Gerente gerenteResponsavel(){
        return gerenteResponsavel;
    }
}
