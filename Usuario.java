import java.util.List;

public class Usuario {
    private String nome;
    private String login;
    private String senha;
    private String email;
    private String sala;
    private String ramal;
    private String telefone;

    public Usuario(String nome, String login, String senha, String email, String sala, String ramal, String telefone) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.sala = sala;
        this.ramal = ramal;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSala() {
        return sala;
    }

    public void setSala(String sala) {
        this.sala = sala;
    }

    public String getRamal() {
        return ramal;
    }

    public void setRamal(String ramal) {
        this.ramal = ramal;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void cadastrarUsuario(List<Usuario> usuarios) {
        usuarios.add(this);
    }
}