package sistema_do_petshop;

public class Agendamento {
	private Animal animal;
	private Servico servico;
	private String data;
	private String horario;
	private String status;
	
	public Agendamento(Animal animal, Servico servico, String data, String horario, String status) {
		super();
		this.animal = animal;
		this.servico = servico;
		this.data = data;
		this.horario = horario;
		this.status = status;
	}
	
	public Animal getAnimal() {
		return animal;
	}

	public void setAnimal(Animal animal) {
		this.animal = animal;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void exibirAgendamento () {
		System.out.println("Agendamento: " +animal.getNome()+ "\nServiço: " +servico.getTipo()+ "\nData: " +data+ " às " +horario+ "\nStatus: " +status);
	}
	
}
