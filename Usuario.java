public class Usuario {
    protected String login;
    protected String senha;
    protected String email;
    protected String sala;
    protected String ramal;
    protected String telefone;

    public Usuario (String login, String senha, String email, String sala, String ramal, String telefone){
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.sala = sala;
        this.ramal = ramal;
        this.telefone = telefone;
    }

    public void exibirDados(){
        System.out.println("Login: " + login);
        System.out.println("Senha: " + senha);
        System.out.println("E-mail: " + email);
        System.out.println("Sala: " + sala);
        System.out.println("Ramal: " + ramal);
        System.out.println("Telefone: " + telefone);
    }
}