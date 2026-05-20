package sistema_do_petshop;

public class Cliente {
	private String nome;
	private String tel;
	private String email;

	Cliente (String nome, String tel, String email) {
		this.nome = nome;
		this.tel = tel;
		this.email = email;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setTelefone (String tel) {
		this.tel = tel;
	}
	
	public String getTel () {
		return tel;
	}
	
	public void setEmail (String email) {
		this.email = email;
	}
	
	public String getEmail () {
		return email;
	}
}
