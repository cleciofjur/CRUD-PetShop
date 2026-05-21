package sistema_do_petshop;

public class Animal {
	private String nome;
	private String especie;
	private String raca;
	// É a declaração de um atributo privado que faz associação ou referência a outro objeto de uma outra classe
	private Cliente dono;
	// private -> modificador de acesso
	// Cliente -> define quec Cliente não é um tipo primitivo, mas sim uma instância de uma classe chamada Cliente
	// dono -> é o nome da varivel que receberá o objeto de Cliente
		
	Animal (String nome, String especie, String raca, Cliente dono) {
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
		this.dono = dono;
	}
	
	public void setNome (String nome) {
		this.nome = nome;
	}
	
	public String getNome () {
		return nome;
	}
	
	public void setEspecie (String especie) {
		this.especie = especie;
	}
	
	public String getEspecie () {
		return especie;
	}
	
	public void setRaca (String raca) {
		this.raca = raca;
	}
	
	public String getRaca () {
		return raca;
	}

	public Cliente getDono() {
		return dono;
	}

	public void setDono(Cliente dono) {
		this.dono = dono;
	}
	
	public void exibirInformacoes () {
		System.out.print("Animal: " +nome+ "\nEspécie: " +especie+ "\nRaça: " +raca+ "\nDono: " +dono.getNome());
	}
}
