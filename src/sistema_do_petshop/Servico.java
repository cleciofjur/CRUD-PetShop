package sistema_do_petshop;

public class Servico {
	String tipo;
	private Double preco;
	String descricao;
	
	Servico (){}
	
	Servico (Double preco) {
		this.preco = preco;
	} 
	
	public void setPreco (Double preco) {
		this.preco = preco;
	}
	
	public Double getPreco () {
		return preco;
	}

	public boolean disponivel(int hour) {
		// TODO Auto-generated method stub
		return false;
	}
}
