package sistema_do_petshop;

public class LoginController {
	 private TelaLogin tela;
	    private Usuario   usuario;

	    public LoginController(TelaLogin tela) {
	        this.tela    = tela;
	        // Usuário cadastrado na "base de dados" (hardcoded para o exemplo)
	        this.usuario = new Usuario("admin", "1234", null);
	    }

	    /**
	     * Chamado pela View quando o usuário clica em "Entrar".
	     * @param nome  valor digitado no campo de nome
	     * @param senha valor digitado no campo de senha
	     */
	    public void tentarLogin(String nome, String senha) {
	        // Validação simples de campos vazios
	        if (nome.isBlank() || senha.isBlank()) {
	            tela.mostrarErro("Preencha todos os campos.");
	            return;
	        }

	        // Delega a regra de autenticação ao Model
	        if (usuario.autenticar(nome, senha)) {
	            tela.irParaAgendamento(nome); // navega para a próxima tela
	        } else {
	            tela.mostrarErro("Usuário ou senha incorretos.");
	        }
	    }
}
