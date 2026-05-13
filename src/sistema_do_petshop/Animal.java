package sistema_do_petshop;

public class Animal {
	private String nome;
	private String especie;
	private String raca;
	private Cliente dono;
		
	Animal (String nome, String especie, String raca, Cliente dono) {
		this.nome = nome;
		this.especie = especie;
		this.raca = raca;
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
