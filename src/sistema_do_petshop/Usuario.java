package sistema_do_petshop;

public class Usuario {
	private String nome;
	private String senha;
	private String cargo;
	
	// Construtor com parametro vazio
	Usuario() {}

	// Construtor com a passagem de parâmetro
	Usuario (String nome, String senha, String cargo) {
		this.nome = nome; // "this" faz referência ao atributo da classe
		this.senha = senha;
		this.cargo = cargo;
	}

	// Getters e Setter são modificadores de acesso na linguagem, eles servem para modificar ou acessar os atributos privados de forma controlada e correta

	// o Set faz com que valor seja lido
	public void setNome (String nome) {
		this.nome = nome;
	}

	// o Get define ou atualiza o valor permitindo validações
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

	// "public boolean" é uma função que retorna um valor 'True' ou 'False' dependendo da condição
	// nomeTentativa e senhaTentativa são as variavéis que o usuário digitou 
	public boolean autenticar (String nomeTentaiva, String senhaTentativa) {
		// 'this.nome.equals' ou 'this.senha.equals' faz uma verificação se os dados digitados pelo usuário são os mesmos da base de dados
		return this.nome.equals(nomeTentaiva) && this.senha.equals(senhaTentativa);
	}
}


