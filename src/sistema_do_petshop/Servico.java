package sistema_do_petshop;

public class Servico {
	private String tipo;
	private Double preco;
	private String descricao;

	public Servico(String tipo, Double preco, String descricao) {
		this.tipo = tipo;
		this.preco = preco;
		this.descricao = descricao;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	// Função que quando chama na classe principal permite a exibição da informação como está abaixo
	public void exibirServico() {
		System.out.println("Serviço: " +tipo+ "\nPreço: R$ " +preco+ "\nDescrição: ");
	}
}
