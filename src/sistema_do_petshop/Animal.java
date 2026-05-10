package sistema_do_petshop;

public class Animal {
	private String nome;
	private String especie;
	private String raca;
	
	Animal() {}
	
	Animal (String nome, String especie, String raca) {
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
}
