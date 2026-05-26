package sistema_do_petshop;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;

public class TelaAgendamento extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final Color COR_FUNDO      = new Color(5, 63, 92);
    private static final Color COR_AZUL_MEDIO = new Color(66, 158, 189);
    private static final Color COR_AMBAR      = new Color(247, 173, 25);
    private static final Color COR_AZUL_CLARO = new Color(159, 231, 245);
    private static final Color COR_BRANCO     = Color.WHITE;

    private static final int LBL_X = 20;
    private static final int LBL_W = 140;
    private static final int LBL_H = 25;
    private static final int TXT_X = 170;
    private static final int TXT_W = 180;
    private static final int BTN_H = 32;

    JLabel lblAnimal, lblDono, lblServico, lblData, lblHorario;

    JTextField txtDono, txtData, txtHorario;

    // Lista suspensa que puxa os dados cadastrados na página anterior
    JComboBox<String> cmbAnimal;
    JComboBox<String> cmbServico;

    JTextArea areaExibicao;

    JButton btnAgendar, btnExcluir, btnConsultarAgenda, btnVoltar;

    // Lista de animais
    ArrayList<Animal> listaAnimais;
    
    // Lista vazia da próoria tela
    ArrayList<Agendamento> listaAgendamentos;

    // Detecta a melhor fonte com suporte a emoji disponível no sistema
    private static Font getFonteComEmoji(int estilo, int tamanho) {
        List<String> candidatas = Arrays.asList(
            "Segoe UI Emoji",    // Windows
            "Apple Color Emoji", // macOS
            "Noto Color Emoji",  // Linux
            "Segoe UI"           // fallback sem emoji
        );

        List<String> instaladas = Arrays.asList(
            GraphicsEnvironment.getLocalGraphicsEnvironment()
                               .getAvailableFontFamilyNames()
        );

        for (String nome : candidatas) {
            if (instaladas.contains(nome)) {
                return new Font(nome, estilo, tamanho);
            }
        }
        return new Font("Dialog", estilo, tamanho);
    }

    
    // Método que pede do construtor uma lista para funcionar
    public TelaAgendamento(ArrayList<Animal> listaAnimais) {

    	// Atributo 			// parametro recebido pelo constutor
        this.listaAnimais     = listaAnimais;
        
        // Nasce uma lista vazia dentro da mesma classe
        this.listaAgendamentos = new ArrayList<>();

        setTitle("Sistema Pet Shop - Agendamento de Serviços");
        setSize(700, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(COR_FUNDO);
        setContentPane(painel);

        JLabel lblTitulo = new JLabel("📅  Agendamento de Serviços");
        lblTitulo.setBounds(20, 15, 400, 30);
        lblTitulo.setFont(getFonteComEmoji(Font.BOLD, 18));  // ← fonte com emoji
        lblTitulo.setForeground(COR_AMBAR);
        painel.add(lblTitulo);

        JLabel lblSub = new JLabel("Selecione o animal e o serviço desejado");
        lblSub.setBounds(20, 45, 400, 18);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(COR_AZUL_CLARO);
        painel.add(lblSub);

        int[] linhasY = {80, 115, 150, 185, 220};
        String[] nomes = {
            "Animal:",
            "Dono:",
            "Serviço:",
            "Data (dd/mm/aaaa):",
            "Horário (hh:mm):"
        };

        for (int i = 0; i < nomes.length; i++) {
            JLabel lbl = new JLabel(nomes[i]);
            lbl.setBounds(LBL_X, linhasY[i], LBL_W, LBL_H);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl.setForeground(COR_BRANCO);
            painel.add(lbl);

            switch (i) {
                case 0: lblAnimal  = lbl; break;
                case 1: lblDono    = lbl; break;
                case 2: lblServico = lbl; break;
                case 3: lblData    = lbl; break;
                case 4: lblHorario = lbl; break;
            }
        }

        cmbAnimal = new JComboBox<>();
        for (Animal a : listaAnimais)
            cmbAnimal.addItem(a.getNome());
        cmbAnimal.setBounds(TXT_X, linhasY[0], TXT_W, LBL_H);
        painel.add(cmbAnimal);

        txtDono = new JTextField();
        txtDono.setEditable(false);
        txtDono.setBounds(TXT_X, linhasY[1], TXT_W, LBL_H);
        txtDono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDono.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        painel.add(txtDono);

        cmbServico = new JComboBox<>();
        cmbServico.addItem("Banho");
        cmbServico.addItem("Tosa");
        cmbServico.addItem("Consulta Veterinária");
        cmbServico.setBounds(TXT_X, linhasY[2], TXT_W, LBL_H);
        painel.add(cmbServico);

        txtData    = criarCampo(linhasY[3]);
        txtHorario = criarCampo(linhasY[4]);
        painel.add(txtData);
        painel.add(txtHorario);

        int btnLargura  = 190;
        int espacamento = 10;
        int xInicio     = 55;

        btnAgendar         = criarBotao("Agendar",          COR_AMBAR,      COR_FUNDO);
        btnExcluir         = criarBotao("Excluir",          COR_AZUL_MEDIO, COR_BRANCO);
        btnConsultarAgenda = criarBotao("Consultar Agenda", COR_AZUL_MEDIO, COR_BRANCO);

        btnAgendar.setBounds(xInicio, 265, btnLargura, BTN_H);
        btnExcluir.setBounds(xInicio + btnLargura + espacamento, 265, btnLargura, BTN_H);
        btnConsultarAgenda.setBounds(xInicio + (btnLargura + espacamento) * 2, 265, btnLargura, BTN_H);

        painel.add(btnAgendar);
        painel.add(btnExcluir);
        painel.add(btnConsultarAgenda);

        btnVoltar = criarBotao("← Voltar", COR_AZUL_MEDIO, COR_BRANCO);
        btnVoltar.setBounds(250, 308, 200, BTN_H);
        painel.add(btnVoltar);

        areaExibicao = new JTextArea();
        areaExibicao.setEditable(false);
        areaExibicao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaExibicao.setForeground(COR_FUNDO);
        areaExibicao.setBackground(new Color(240, 250, 255));
        areaExibicao.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));

        JScrollPane scroll = new JScrollPane(areaExibicao);
        scroll.setBounds(20, 355, 645, 160);
        scroll.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        painel.add(scroll);

        btnAgendar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultarAgenda.addActionListener(this);
        btnVoltar.addActionListener(this);
        cmbAnimal.addActionListener(this);

        preencherDono();
        setVisible(true);
    }

    private JTextField criarCampo(int y) {
        JTextField f = new JTextField();
        f.setBounds(TXT_X, y, TXT_W, LBL_H);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        return f;
    }

    private JButton criarBotao(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        return btn;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cmbAnimal)         preencherDono();
        if (e.getSource() == btnAgendar)         agendarServico();
        if (e.getSource() == btnExcluir)         excluirAgendamento();
        if (e.getSource() == btnConsultarAgenda) consultarAgenda();

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

        String data    = txtData.getText().trim();
        String horario = txtHorario.getText().trim();

        if (data.isEmpty() || horario.isEmpty()) {
            areaExibicao.setText("Preencha a data e o horário.");
            return;
        }

        Animal animalSelecionado = listaAnimais.get(indice);
        String tipoServico       = (String) cmbServico.getSelectedItem();

        Servico     servico     = new Servico(tipoServico, 0.0, "");
        Agendamento agendamento = new Agendamento(
            animalSelecionado, servico, data, horario, "Agendado"
        );

        listaAgendamentos.add(agendamento);

        areaExibicao.setText(
            "Agendamento realizado com sucesso!\n" +
            "Animal: "     + animalSelecionado.getNome() +
            " | Serviço: " + tipoServico +
            " | Data: "    + data +
            " às "         + horario
        );

        limparCampos();
    }

    private void excluirAgendamento() {
        String nomeAnimal = (String) cmbAnimal.getSelectedItem();

        if (nomeAnimal == null || nomeAnimal.isEmpty()) {
            areaExibicao.setText("Selecione um animal para excluir o agendamento.");
            return;
        }

        for (int i = 0; i < listaAgendamentos.size(); i++) {
            if (listaAgendamentos.get(i).getAnimal().getNome()
                    .equalsIgnoreCase(nomeAnimal)) {
                listaAgendamentos.remove(i);
                areaExibicao.setText(
                    "Agendamento de '" + nomeAnimal + "' excluído com sucesso!"
                );
                return;
            }
        }

        areaExibicao.setText(
            "Nenhum agendamento encontrado para '" + nomeAnimal + "'."
        );
    }

    private void consultarAgenda() {
        if (listaAgendamentos.isEmpty()) {
            areaExibicao.setText("Nenhum agendamento cadastrado.");
            return;
        }

        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < listaAgendamentos.size(); i++) {
            Agendamento ag = listaAgendamentos.get(i);

            resultado.append("Agendamento ").append(i + 1).append(":\n");
            resultado.append("  Animal   : ").append(ag.getAnimal().getNome()).append("\n");
            resultado.append("  Dono     : ").append(ag.getAnimal().getDono().getNome()).append("\n");
            resultado.append("  Serviço  : ").append(ag.getServico().getTipo()).append("\n");
            resultado.append("  Data     : ").append(ag.getData()).append("\n");
            resultado.append("  Horário  : ").append(ag.getHorario()).append("\n");
            resultado.append("  Status   : ").append(ag.getStatus()).append("\n");

            if (i < listaAgendamentos.size() - 1) {
                resultado.append("  -----------------------------------\n");
            }
        }

        areaExibicao.setText(resultado.toString());
    }

    private void limparCampos() {
        txtData.setText("");
        txtHorario.setText("");
    }
}