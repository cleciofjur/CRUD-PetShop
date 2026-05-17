package sistema_do_petshop;

import java.time.LocalDateTime;

public class Agendamento extends Usuario {

    private int id;
    private LocalDateTime datahora;
    private String status;

    private Animal animal;
    private Servico servico;
    private Usuario funcionario;

    public Agendamento(int id, LocalDateTime datahora, Animal animal, Servico servico, Usuario funcionario) {
        this.id = id;
        this.datahora = datahora;
        this.animal = animal;
        this.funcionario = funcionario;
        this.servico = servico;
        this.status = "pendente";
    }

    public boolean agendar() {
        if(servico.disponivel(datahora.getHour())) {
            this.status = "pendente";
            return true;
        }else {
            System.out.println("Horário indisponivel!");
            return false;
        }
    }

    public void cancelar() {
        this.status = "Cancelado";
    }

    public void concluir() {
        this.status = "Concluir";
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataHora() {
        return datahora;
    }

    public String getStatus() {
        return status;
    }

    public Animal getAnimal() {
        return animal;
    }

    public Servico getServico() {
        return servico;
    }

    public Usuario getFuncionario() {
        return funcionario;
    }
}

