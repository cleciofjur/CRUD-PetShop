package sistema_do_petshop;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.Color;
import java.awt.Image;
import javax.swing.ImageIcon;

public class TelaAgendamento extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    JLabel lblAnimal, lblDono, lblServico, lblData, lblHorario;
    JTextField txtDono, txtData, txtHorario;
    JComboBox<String> cmbAnimal;
    JComboBox<String> cmbServico;
    JTextArea areaExibicao;
    JButton btnAgendar, btnExcluir, btnConsultar, btnListarTodos, btnVoltar;
    ArrayList<Animal> listaAnimais;
    ArrayList<Agendamento> listaAgendamentos;

    public TelaAgendamento(ArrayList<Animal> listaAnimais) {
        this.listaAnimais = listaAnimais;
        this.listaAgendamentos = new ArrayList<>();

        setTitle("Sistema Pet Shop - Agendamento de Serviços");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // painel azul
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(new Color(100, 180, 255));
        setContentPane(painel);
        // imagem canto superior direito
        JLabel lblImagem = new JLabel();
        ImageIcon icone = new ImageIcon("C:/Users/eluan/Downloads/CRUD-PetShop-main/CRUD-PetShop-main/src/Imagens/15-dog-png-image-picture-download-dogs.png");
        Image img = icone.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        lblImagem.setIcon(new ImageIcon(img));
        lblImagem.setBounds(350, 70, 120, 120);
        painel.add(lblImagem);

        lblAnimal = new JLabel("Animal:");
        lblAnimal.setBounds(20, 20, 130, 25);
        painel.add(lblAnimal);

        cmbAnimal = new JComboBox<>();
        for (Animal a : listaAnimais) {
            cmbAnimal.addItem(a.getNome());
        }
        cmbAnimal.setBounds(155, 20, 180, 25);
        painel.add(cmbAnimal);

        lblDono = new JLabel("Dono:");
        lblDono.setBounds(20, 55, 130, 25);
        painel.add(lblDono);

        txtDono = new JTextField();
        txtDono.setEditable(false);
        txtDono.setBounds(155, 55, 180, 25);
        painel.add(txtDono);

        lblServico = new JLabel("Serviço:");
        lblServico.setBounds(20, 90, 130, 25);
        painel.add(lblServico);

        cmbServico = new JComboBox<>();
        cmbServico.addItem("Banho");
        cmbServico.addItem("Tosa");
        cmbServico.addItem("Consulta Veterinária");
        cmbServico.setBounds(155, 90, 180, 25);
        painel.add(cmbServico);

        lblData = new JLabel("Data (dd/mm/aaaa):");
        lblData.setBounds(20, 125, 130, 25);
        painel.add(lblData);

        txtData = new JTextField();
        txtData.setBounds(155, 125, 180, 25);
        painel.add(txtData);

        lblHorario = new JLabel("Horário (hh:mm):");
        lblHorario.setBounds(20, 160, 130, 25);
        painel.add(lblHorario);

        txtHorario = new JTextField();
        txtHorario.setBounds(155, 160, 180, 25);
        painel.add(txtHorario);

        btnAgendar = new JButton("Agendar");
        btnAgendar.setBounds(20, 210, 120, 30);
        painel.add(btnAgendar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(150, 210, 120, 30);
        painel.add(btnExcluir);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(280, 210, 120, 30);
        painel.add(btnConsultar);

        btnListarTodos = new JButton("Listar Todos");
        btnListarTodos.setBounds(410, 210, 120, 30);
        painel.add(btnListarTodos);

        btnVoltar = new JButton("← Voltar");
        btnVoltar.setBounds(20, 255, 120, 30);
        painel.add(btnVoltar);

        areaExibicao = new JTextArea();
        areaExibicao.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaExibicao);
        scroll.setBounds(20, 300, 640, 200);
        painel.add(scroll);

        btnAgendar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnListarTodos.addActionListener(this);
        btnVoltar.addActionListener(this);
        cmbAnimal.addActionListener(this);

        preencherDono();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbAnimal) preencherDono();
        if (e.getSource() == btnAgendar) agendarServico();
        if (e.getSource() == btnExcluir) excluirAgendamento();
        if (e.getSource() == btnConsultar) consultarAgendamento();
        if (e.getSource() == btnListarTodos) listarTodos();
        if (e.getSource() == btnVoltar) {
            dispose();
            new TelaPrincipal();
        }
    }

    private void preencherDono() {
        int indice = cmbAnimal.getSelectedIndex();
        if (indice >= 0 && indice < listaAnimais.size()) {
            txtDono.setText(listaAnimais.get(indice).getDono().getNome());
        } else {
            txtDono.setText("");
        }
    }

    private void agendarServico() {
        int indice = cmbAnimal.getSelectedIndex();
        if (indice < 0 || listaAnimais.isEmpty()) {
            areaExibicao.setText("Nenhum animal disponível para agendar.");
            return;
        }
        String data = txtData.getText().trim();
        String horario = txtHorario.getText().trim();
        if (data.isEmpty() || horario.isEmpty()) {
            areaExibicao.setText("Preencha a data e o horário.");
            return;
        }
        Animal animalSelecionado = listaAnimais.get(indice);
        String tipoServico = (String) cmbServico.getSelectedItem();
        Servico servico = new Servico(tipoServico, 0.0, "");
        Agendamento agendamento = new Agendamento(animalSelecionado, servico, data, horario, "Agendado");
        listaAgendamentos.add(agendamento);
        areaExibicao.setText("Agendamento realizado com sucesso!\n" +
                "Animal: " + animalSelecionado.getNome() +
                " | Serviço: " + tipoServico +
                " | Data: " + data + " às " + horario);
        limparCampos();
    }

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

    private void limparCampos() {
        txtData.setText("");
        txtHorario.setText("");
    }
}