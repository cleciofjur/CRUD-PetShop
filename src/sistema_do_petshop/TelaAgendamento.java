package sistema_do_petshop;

import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

public class TelaAgendamento extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    // Constantes de cores utilizadas na interface
    private static final Color COR_FUNDO      = new Color(5, 63, 92);
    private static final Color COR_AZUL_MEDIO = new Color(66, 158, 189);
    private static final Color COR_AMBAR      = new Color(247, 173, 25);
    private static final Color COR_AZUL_CLARO = new Color(159, 231, 245);
    private static final Color COR_BRANCO     = Color.WHITE;

    // Constantes de posicionamento dos componentes
    private static final int LBL_X = 20;
    private static final int LBL_W = 140;
    private static final int LBL_H = 25;
    private static final int TXT_X = 170;
    private static final int TXT_W = 180;
    private static final int BTN_H = 32;

    // Labels da interface
    JLabel lblAnimal, lblDono, lblServico, lblData, lblHorario;

    // Campos de texto
    JTextField txtDono, txtData, txtHorario;

    // Caixas de seleção suspensa
    JComboBox<String> cmbAnimal;
    JComboBox<String> cmbServico;

    // Área de exibição de resultados
    JTextArea areaExibicao;

    JButton btnAgendar, btnExcluir, btnConsultarAgenda, btnVoltar;

    // Lista de animais recebida da tela principal
    ArrayList<Animal> listaAnimais;

    // Lista de agendamentos criados nesta tela
    ArrayList<Agendamento> listaAgendamentos;

    // Construtor da tela
    public TelaAgendamento(ArrayList<Animal> listaAnimais) {

        // Recebe a lista de animais cadastrados na tela anterior
        this.listaAnimais = listaAnimais;

        // Inicializa a lista de agendamentos vazia
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

        // Título principal da tela
        JLabel lblTitulo = new JLabel("📅  Agendamento de Serviços");
        lblTitulo.setBounds(20, 15, 400, 30);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COR_AMBAR);
        painel.add(lblTitulo);

        // Subtítulo explicativo
        JLabel lblSub = new JLabel("Selecione o animal e o serviço desejado");
        lblSub.setBounds(20, 45, 400, 18);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(COR_AZUL_CLARO);
        painel.add(lblSub);

        // Posições verticais de cada linha do formulário
        int[] linhasY = {80, 115, 150, 185, 220};

        // Textos de cada label do formulário
        String[] nomes = {
            "Animal:",
            "Dono:",
            "Serviço:",
            "Data (dd/mm/aaaa):",
            "Horário (hh:mm):"
        };

        // Cria todos os labels automaticamente usando FOR
        for (int i = 0; i < nomes.length; i++) {

            JLabel lbl = new JLabel(nomes[i]);
            lbl.setBounds(LBL_X, linhasY[i], LBL_W, LBL_H);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl.setForeground(COR_BRANCO);
            painel.add(lbl);

            // Associa cada label criado à variável correspondente
            switch (i) {
                case 0: lblAnimal  = lbl; break;
                case 1: lblDono    = lbl; break;
                case 2: lblServico = lbl; break;
                case 3: lblData    = lbl; break;
                case 4: lblHorario = lbl; break;
            }
        }

        // ComboBox para selecionar o animal cadastrado
        cmbAnimal = new JComboBox<>();

        // Percorre a lista e adiciona cada animal ao ComboBox
        for (Animal a : listaAnimais)
            cmbAnimal.addItem(a.getNome());

        cmbAnimal.setBounds(TXT_X, linhasY[0], TXT_W, LBL_H);
        painel.add(cmbAnimal);

        // Campo do dono preenchido automaticamente (não editável)
        txtDono = new JTextField();
        txtDono.setEditable(false);
        txtDono.setBounds(TXT_X, linhasY[1], TXT_W, LBL_H);
        txtDono.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtDono.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        painel.add(txtDono);

        // ComboBox com os tipos de serviço disponíveis
        cmbServico = new JComboBox<>();
        cmbServico.addItem("Banho");
        cmbServico.addItem("Tosa");
        cmbServico.addItem("Consulta Veterinária");
        cmbServico.setBounds(TXT_X, linhasY[2], TXT_W, LBL_H);
        painel.add(cmbServico);

        // Campos de data e horário do agendamento
        txtData    = criarCampo(linhasY[3]);
        txtHorario = criarCampo(linhasY[4]);
        painel.add(txtData);
        painel.add(txtHorario);

        // ── Botões ──────────────────────────────────────────────────────────
        // MUDANÇA 3: Botões organizados em duas fileiras centralizadas
        //
        // Fileira 1: Agendar | Excluir | Consultar Agenda  (3 botões iguais)
        // Fileira 2: Voltar                                 (centralizado)

        // Calcula posição inicial para centralizar 3 botões na janela de 700px
        // Largura de cada botão: 190px | Espaço entre eles: 10px
        // Total: 3 * 190 + 2 * 10 = 590  →  margem: (700 - 590) / 2 = 55
        int btnLargura = 190;
        int espacamento = 10;
        int xInicio = 55;

        // Cria os três botões da fileira principal
        btnAgendar        = criarBotao("Agendar",          COR_AMBAR,      COR_FUNDO);
        btnExcluir        = criarBotao("Excluir",          COR_AZUL_MEDIO, COR_BRANCO);

        // MUDANÇA 2: "Consultar" renomeado para "Consultar Agenda"
        btnConsultarAgenda = criarBotao("Consultar Agenda", COR_AZUL_MEDIO, COR_BRANCO);

        // Posiciona os botões da fileira 1
        btnAgendar.setBounds(xInicio, 265, btnLargura, BTN_H);
        btnExcluir.setBounds(xInicio + btnLargura + espacamento, 265, btnLargura, BTN_H);
        btnConsultarAgenda.setBounds(xInicio + (btnLargura + espacamento) * 2, 265, btnLargura, BTN_H);

        painel.add(btnAgendar);
        painel.add(btnExcluir);
        painel.add(btnConsultarAgenda);


        btnVoltar = criarBotao("← Voltar", COR_AZUL_MEDIO, COR_BRANCO);
        btnVoltar.setBounds(250, 308, 200, BTN_H);
        painel.add(btnVoltar);

        // ── Área de exibição ─────────────────────────────────────────────────

        areaExibicao = new JTextArea();
        areaExibicao.setEditable(false);
        areaExibicao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaExibicao.setForeground(COR_FUNDO);
        areaExibicao.setBackground(new Color(240, 250, 255));
        areaExibicao.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));

        // JScrollPane adiciona barra de rolagem à área de exibição
        JScrollPane scroll = new JScrollPane(areaExibicao);
        scroll.setBounds(20, 355, 645, 160);
        scroll.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        painel.add(scroll);

        // Registra os eventos de clique de cada botão
        btnAgendar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultarAgenda.addActionListener(this);
        btnVoltar.addActionListener(this);

        // Detecta quando o animal selecionado no ComboBox muda
        cmbAnimal.addActionListener(this);

        // Preenche o campo dono assim que a tela abre
        preencherDono();

        setVisible(true);
    }

    // Cria um campo de texto padronizado na posição Y informada
    private JTextField criarCampo(int y) {
        JTextField f = new JTextField();
        f.setBounds(TXT_X, y, TXT_W, LBL_H);
        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        f.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        return f;
    }

    // Cria um botão padronizado com texto, cor de fundo e cor do texto
    private JButton criarBotao(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        return btn;
    }

    // Método executado automaticamente ao clicar em qualquer botão registrado
    @Override
    public void actionPerformed(ActionEvent e) {

        // Atualiza o campo dono quando o animal selecionado muda
        if (e.getSource() == cmbAnimal)
            preencherDono();

        if (e.getSource() == btnAgendar)
            agendarServico();

        if (e.getSource() == btnExcluir)
            excluirAgendamento();

        // MUDANÇA 2: Agora chama consultarAgenda() no lugar de consultarAgendamento()
        if (e.getSource() == btnConsultarAgenda)
            consultarAgenda();

        // Volta para a tela principal ao clicar em Voltar
        if (e.getSource() == btnVoltar) {
            dispose();
            new TelaPrincipal();
        }

    }

    // Preenche o campo "Dono" automaticamente com base no animal selecionado
    private void preencherDono() {

        // getSelectedIndex() retorna a posição do item selecionado no ComboBox
        int indice = cmbAnimal.getSelectedIndex();

        // Verifica se o índice é válido antes de acessar a lista
        if (indice >= 0 && indice < listaAnimais.size()) {
            txtDono.setText(listaAnimais.get(indice).getDono().getNome());
        } else {
            txtDono.setText("");
        }
    }

    // Cria e salva um novo agendamento com os dados preenchidos
    private void agendarServico() {

        int indice = cmbAnimal.getSelectedIndex();

        // Verifica se há animais disponíveis para agendar
        if (indice < 0 || listaAnimais.isEmpty()) {
            areaExibicao.setText("Nenhum animal disponível para agendar.");
            return;
        }

        String data    = txtData.getText().trim();
        String horario = txtHorario.getText().trim();

        // Verifica se data e horário foram preenchidos
        if (data.isEmpty() || horario.isEmpty()) {
            areaExibicao.setText("Preencha a data e o horário.");
            return;
        }

        // Obtém o animal e o serviço selecionados
        Animal animalSelecionado = listaAnimais.get(indice);
        String tipoServico       = (String) cmbServico.getSelectedItem();

        // Cria os objetos necessários para o agendamento
        Servico servico      = new Servico(tipoServico, 0.0, "");
        Agendamento agendamento = new Agendamento(
            animalSelecionado, servico, data, horario, "Agendado"
        );

        // Adiciona o agendamento na lista
        listaAgendamentos.add(agendamento);

        // Exibe mensagem de confirmação
        areaExibicao.setText(
            "Agendamento realizado com sucesso!\n" +
            "Animal: "  + animalSelecionado.getNome() +
            " | Serviço: " + tipoServico +
            " | Data: "    + data +
            " às "         + horario
        );

        limparCampos();
    }

    private void excluirAgendamento() {

        // Obtém o nome do animal selecionado no ComboBox
        String nomeAnimal = (String) cmbAnimal.getSelectedItem();

        // Verifica se existe algum animal selecionado
        if (nomeAnimal == null || nomeAnimal.isEmpty()) {
            areaExibicao.setText("Selecione um animal para excluir o agendamento.");
            return;
        }

        // Percorre a lista procurando o agendamento desse animal
        for (int i = 0; i < listaAgendamentos.size(); i++) {

            // equalsIgnoreCase() compara sem diferenciar maiúsculas/minúsculas
            if (listaAgendamentos.get(i).getAnimal().getNome()
                    .equalsIgnoreCase(nomeAnimal)) {

                // Remove o agendamento encontrado
                listaAgendamentos.remove(i);

                areaExibicao.setText(
                    "Agendamento de '" + nomeAnimal + "' excluído com sucesso!"
                );

                return; // Sai do método após excluir
            }
        }

        // Caso não encontre nenhum agendamento para esse animal
        areaExibicao.setText(
            "Nenhum agendamento encontrado para '" + nomeAnimal + "'."
        );
    }
    
    private void consultarAgenda() {
        if (listaAgendamentos.isEmpty()) {
            areaExibicao.setText("Nenhum agendamento cadastrado.");
            return;
        }

        // StringBuilder monta o texto de forma eficiente
        StringBuilder resultado = new StringBuilder();

        // Percorre todos os agendamentos numerando cada um
        for (int i = 0; i < listaAgendamentos.size(); i++) {

            Agendamento ag = listaAgendamentos.get(i);

            // Cabeçalho do bloco com número do agendamento
            resultado.append("Agendamento ").append(i + 1).append(":\n");
            resultado.append("  Animal   : ").append(ag.getAnimal().getNome()).append("\n");
            resultado.append("  Dono     : ").append(ag.getAnimal().getDono().getNome()).append("\n");
            resultado.append("  Serviço  : ").append(ag.getServico().getTipo()).append("\n");
            resultado.append("  Data     : ").append(ag.getData()).append("\n");
            resultado.append("  Horário  : ").append(ag.getHorario()).append("\n");
            resultado.append("  Status   : ").append(ag.getStatus()).append("\n");

            // Linha separadora entre agendamentos, exceto no último
            if (i < listaAgendamentos.size() - 1) {
                resultado.append("  -----------------------------------\n");
            }
        }

        areaExibicao.setText(resultado.toString());
    }

    // Limpa os campos de data e horário após um agendamento
    private void limparCampos() {
        txtData.setText("");
        txtHorario.setText("");
    }
}