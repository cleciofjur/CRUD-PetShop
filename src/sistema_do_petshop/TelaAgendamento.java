package sistema_do_petshop;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class TelaAgendamento extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// --- Campos de entrada ---
    JLabel lblAnimal, lblDono, lblServico, lblData, lblHorario;
    JTextField txtDono, txtData, txtHorario;
    JComboBox<String> cmbAnimal;   // lista suspensa com os animais cadastrados
    JComboBox<String> cmbServico;  // lista suspensa com os tipos de serviço

    // --- Área de exibição ---
    JTextArea areaExibicao;

    // --- Botões ---
    JButton btnAgendar, btnExcluir, btnConsultar, btnListarTodos, btnVoltar;

    // --- Listas de dados ---
    ArrayList<Animal> listaAnimais;
    ArrayList<Agendamento> listaAgendamentos;

    // --- Construtor recebe a lista de animais da TelaPrincipal ---
    public TelaAgendamento(ArrayList<Animal> listaAnimais) {
        this.listaAnimais = listaAnimais;
        this.listaAgendamentos = new ArrayList<>();

        setTitle("Sistema Pet Shop - Agendamento de Serviços");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // --- Animal (JComboBox populado com os animais cadastrados) ---
        lblAnimal = new JLabel("Animal:");
        lblAnimal.setBounds(20, 20, 130, 25);
        add(lblAnimal);

        cmbAnimal = new JComboBox<>();
        for (Animal a : listaAnimais) {
            cmbAnimal.addItem(a.getNome()); // adiciona o nome de cada animal na lista
        }
        cmbAnimal.setBounds(155, 20, 180, 25);
        add(cmbAnimal);

        // --- Dono (preenchido automaticamente ao selecionar o animal) ---
        lblDono = new JLabel("Dono:");
        lblDono.setBounds(20, 55, 130, 25);
        add(lblDono);

        txtDono = new JTextField();
        txtDono.setEditable(false); // só leitura, preenchido automaticamente
        txtDono.setBounds(155, 55, 180, 25);
        add(txtDono);

        // --- Tipo de serviço ---
        lblServico = new JLabel("Serviço:");
        lblServico.setBounds(20, 90, 130, 25);
        add(lblServico);

        cmbServico = new JComboBox<>();
        cmbServico.addItem("Banho");
        cmbServico.addItem("Tosa");
        cmbServico.addItem("Consulta Veterinária");
        cmbServico.setBounds(155, 90, 180, 25);
        add(cmbServico);

        // --- Data ---
        lblData = new JLabel("Data (dd/mm/aaaa):");
        lblData.setBounds(20, 125, 130, 25);
        add(lblData);

        txtData = new JTextField();
        txtData.setBounds(155, 125, 180, 25);
        add(txtData);

        // --- Horário ---
        lblHorario = new JLabel("Horário (hh:mm):");
        lblHorario.setBounds(20, 160, 130, 25);
        add(lblHorario);

        txtHorario = new JTextField();
        txtHorario.setBounds(155, 160, 180, 25);
        add(txtHorario);

        // --- Botões ---
        btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(20, 210, 120, 30);
        add(btnAgendar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(150, 210, 120, 30);
        add(btnExcluir);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(280, 210, 120, 30);
        add(btnConsultar);

        btnListarTodos = new JButton("Listar Todos");
        btnListarTodos.setBounds(410, 210, 120, 30);
        add(btnListarTodos);

        btnVoltar = new JButton("← Voltar");
        btnVoltar.setBounds(20, 255, 120, 30);
        add(btnVoltar);

        // --- Área de exibição com scroll ---
        areaExibicao = new JTextArea();
        areaExibicao.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaExibicao);
        scroll.setBounds(20, 300, 640, 200);
        add(scroll);

        // --- Registra os botões e o combo de animal ---
        btnAgendar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnListarTodos.addActionListener(this);
        btnVoltar.addActionListener(this);

        // Quando o usuário troca o animal, o dono é preenchido automaticamente
        cmbAnimal.addActionListener(this);

        // Preenche o dono já na abertura, se houver animais
        preencherDono();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Quando muda o animal selecionado, atualiza o campo dono
        if (e.getSource() == cmbAnimal) {
            preencherDono();
        }

        if (e.getSource() == btnAgendar) {
            agendarServico();
        }

        if (e.getSource() == btnExcluir) {
            excluirAgendamento();
        }

        if (e.getSource() == btnConsultar) {
            consultarAgendamento();
        }

        if (e.getSource() == btnListarTodos) {
            listarTodos();
        }

        if (e.getSource() == btnVoltar) {
            dispose();
            new TelaPrincipal(); // volta para a tela principal
        }
    }

    // --- Preenche o campo Dono com base no animal selecionado ---
    private void preencherDono() {
        int indice = cmbAnimal.getSelectedIndex();

        if (indice >= 0 && indice < listaAnimais.size()) {
            Animal animalSelecionado = listaAnimais.get(indice);
            txtDono.setText(animalSelecionado.getDono().getNome());
        } else {
            txtDono.setText("");
        }
    }

    // --- Agendar serviço ---
    private void agendarServico() {
        int indice = cmbAnimal.getSelectedIndex();

        if (indice < 0 || listaAnimais.isEmpty()) {
            areaExibicao.setText("Nenhum animal disponível para agendar.");
            return;
        }

        String data    = txtData.getText().trim();
        String horario = txtHorario.getText().trim();

        if (data.isEmpty() || horario.isEmpty()) {
            areaExibicao.setText("Preencha a data e o horário.");
            return;
        }

        Animal animalSelecionado = listaAnimais.get(indice);
        String tipoServico       = (String) cmbServico.getSelectedItem();

        Servico servico        = new Servico(tipoServico, 0.0, "");
        Agendamento agendamento = new Agendamento(animalSelecionado, servico, data, horario, "Agendado");

        listaAgendamentos.add(agendamento);

        areaExibicao.setText("Agendamento realizado com sucesso!\n" +
            "Animal: " + animalSelecionado.getNome() +
            " | Serviço: " + tipoServico +
            " | Data: " + data + " às " + horario);

        limparCampos();
    }

    // --- Excluir agendamento pelo nome do animal ---
    private void excluirAgendamento() {
        String nomeAnimal = (String) cmbAnimal.getSelectedItem();

        for (int i = 0; i < listaAgendamentos.size(); i++) {
            if (listaAgendamentos.get(i).getAnimal().getNome().equalsIgnoreCase(nomeAnimal)) {
                listaAgendamentos.remove(i);
                areaExibicao.setText("Agendamento de '" + nomeAnimal + "' excluído com sucesso!");
                return;
            }
        }
        areaExibicao.setText("Nenhum agendamento encontrado para '" + nomeAnimal + "'.");
    }

    // --- Consultar agendamento pelo animal selecionado ---
    private void consultarAgendamento() {
        String nomeAnimal = (String) cmbAnimal.getSelectedItem();
        StringBuilder resultado = new StringBuilder();

        for (Agendamento ag : listaAgendamentos) {
            if (ag.getAnimal().getNome().equalsIgnoreCase(nomeAnimal)) {
                resultado.append("Animal: ").append(ag.getAnimal().getNome())
                         .append(" | Serviço: ").append(ag.getServico().getTipo())
                         .append(" | Data: ").append(ag.getData())
                         .append(" às ").append(ag.getHorario())
                         .append(" | Status: ").append(ag.getStatus())
                         .append("\n");
            }
        }

        if (resultado.length() == 0) {
            areaExibicao.setText("Nenhum agendamento encontrado para '" + nomeAnimal + "'.");
        } else {
            areaExibicao.setText(resultado.toString());
        }
    }

    // --- Listar todos os agendamentos ---
    private void listarTodos() {
        if (listaAgendamentos.isEmpty()) {
            areaExibicao.setText("Nenhum agendamento cadastrado.");
            return;
        }

        StringBuilder lista = new StringBuilder("=== Agendamentos ===\n");
        for (Agendamento ag : listaAgendamentos) {
            lista.append("Animal: ").append(ag.getAnimal().getNome())
                 .append(" | Dono: ").append(ag.getAnimal().getDono().getNome())
                 .append(" | Serviço: ").append(ag.getServico().getTipo())
                 .append(" | Data: ").append(ag.getData())
                 .append(" às ").append(ag.getHorario())
                 .append(" | Status: ").append(ag.getStatus())
                 .append("\n");
        }
        areaExibicao.setText(lista.toString());
    }

    // --- Limpa data e horário após agendar ---
    private void limparCampos() {
        txtData.setText("");
        txtHorario.setText("");
    }
}
