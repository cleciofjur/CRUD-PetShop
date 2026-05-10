package sistema_do_petshop;

public class Cliente {
	private String nome;
	private Integer telefone;
	private String email;
	
	Cliente() {}
	
	Cliente (String nome, Integer telefone, String email) {
		this.nome = nome;
		this.telefone = telefone;
		this.email = email;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setTelefone (Integer telefone) {
		this.telefone = telefone;
	}
	
	public Integer getTelefone () {
		return telefone;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getEmail () {
		return email;
	}
}
