package sistema_do_petshop;

public class Usuario {
	private String nome;
	private String senha;
	private String cargo;
	
	Usuario() {}
	
	Usuario (String nome, String senha, String cargo) {
		this.nome = nome;
		this.senha = senha;
		this.cargo = cargo;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setSenha (String senha) {
		this.senha = senha;
	}
	
	public String getSenha () {
		return senha;
	}
	
	public void setCargo (String cargo) {
		this.cargo = cargo;
	}
	
	public String getCargo () {
		return cargo;
	}
	
	void autenticar () {
		
	}
}


