package sistema_do_petshop;

// Importações das classes necessárias para eventos, interface gráfica e listas
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;

// Classe da tela de agendamento
// JFrame -> cria uma janela gráfica
// ActionListener -> permite capturar eventos de clique
public class TelaAgendamento extends JFrame implements ActionListener {

    // Controle de serialização da classe
    private static final long serialVersionUID = 1L;

    // Constantes de cores utilizadas na interface
    private static final Color COR_FUNDO      = new Color(5, 63, 92);
    private static final Color COR_AZUL_MEDIO = new Color(66, 158, 189);
    private static final Color COR_AMBAR      = new Color(247, 173, 25);
    private static final Color COR_AZUL_CLARO = new Color(159, 231, 245);
    private static final Color COR_BRANCO     = Color.WHITE;

    // Constantes de posicionamento e tamanho dos componentes
    private static final int LBL_X = 20;
    private static final int LBL_W = 140;
    private static final int LBL_H = 25;
    private static final int TXT_X = 170;
    private static final int TXT_W = 180;
    private static final int BTN_W = 130;
    private static final int BTN_H = 30;

    // Componentes gráficos da interface
    JLabel lblAnimal, lblDono, lblServico, lblData, lblHorario;
    JTextField txtDono, txtData, txtHorario;

    // JComboBox -> caixa de seleção suspensa
    JComboBox<String> cmbAnimal;
    JComboBox<String> cmbServico;

    // Área onde os resultados e mensagens serão exibidos
    JTextArea areaExibicao;

    // Botões da tela
    JButton btnAgendar, btnExcluir, btnConsultar, btnListarTodos, btnVoltar;

    // Lista de animais cadastrados
    ArrayList<Animal> listaAnimais;

    // Lista de agendamentos realizados
    ArrayList<Agendamento> listaAgendamentos;

    // Método construtor
    public TelaAgendamento(ArrayList<Animal> listaAnimais) {

        // Recebe a lista de animais da tela anterior
        this.listaAnimais = listaAnimais;

        // Inicializa lista de agendamentos vazia
        this.listaAgendamentos = new ArrayList<>();

        // Configurações da janela
        setTitle("Sistema Pet Shop - Agendamento de Serviços");
        setSize(700, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Criação do painel principal
        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(COR_FUNDO);
        setContentPane(painel);

        // ── Título da tela ─────────────────────────────

        JLabel lblTitulo = new JLabel("📅  Agendamento de Serviços");
        lblTitulo.setBounds(20, 15, 400, 30);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COR_AMBAR);
        painel.add(lblTitulo);

        JLabel lblSub = new JLabel("Selecione o animal e o serviço desejado");
        lblSub.setBounds(20, 45, 400, 18);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(COR_AZUL_CLARO);
        painel.add(lblSub);

        // ── Labels ────────────────────────────────────

        int[] linhasY = {80, 115, 150, 185, 220};

        String[] nomes = {
            "Animal:",
            "Dono:",
            "Serviço:",
            "Data (dd/mm/aaaa):",
            "Horário (hh:mm):"
        };

        // FOR -> cria todos os labels automaticamente
        for (int i = 0; i < nomes.length; i++) {

            JLabel lbl = new JLabel(nomes[i]);

            lbl.setBounds(LBL_X, linhasY[i], LBL_W, LBL_H);
            lbl.setFont(new Font("Segoe UI", Font.BOLD, 13));
            lbl.setForeground(COR_BRANCO);

            painel.add(lbl);

            // switch -> identifica qual label está sendo criado
            switch (i) {
                case 0: lblAnimal  = lbl; break;
                case 1: lblDono    = lbl; break;
                case 2: lblServico = lbl; break;
                case 3: lblData    = lbl; break;
                case 4: lblHorario = lbl; break;
            }
        }

        // ── Campos e JComboBox ────────────────────────

        // ComboBox para selecionar animal
        cmbAnimal = new JComboBox<>();

        // FOR EACH -> percorre todos os animais da lista
        for (Animal a : listaAnimais)
            cmbAnimal.addItem(a.getNome());

        cmbAnimal.setBounds(TXT_X, linhasY[0], TXT_W, LBL_H);

        painel.add(cmbAnimal);

        // Campo dono (não editável)
        txtDono = new JTextField();

        txtDono.setEditable(false);

        txtDono.setBounds(TXT_X, linhasY[1], TXT_W, LBL_H);

        txtDono.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        txtDono.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));

        painel.add(txtDono);

        // ComboBox de serviços
        cmbServico = new JComboBox<>();

        cmbServico.addItem("Banho");
        cmbServico.addItem("Tosa");
        cmbServico.addItem("Consulta Veterinária");

        cmbServico.setBounds(TXT_X, linhasY[2], TXT_W, LBL_H);

        painel.add(cmbServico);

        // Campos de data e horário
        txtData = criarCampo(linhasY[3]);
        painel.add(txtData);

        txtHorario = criarCampo(linhasY[4]);
        painel.add(txtHorario);

        // ── Botões ────────────────────────────────────

        btnAgendar     = criarBotao("Agendar", COR_AMBAR, COR_FUNDO);
        btnExcluir     = criarBotao("Excluir", COR_AZUL_MEDIO, COR_BRANCO);
        btnConsultar   = criarBotao("Consultar", COR_AZUL_MEDIO, COR_BRANCO);
        btnListarTodos = criarBotao("Listar Todos", COR_AZUL_MEDIO, COR_BRANCO);

        btnAgendar.setBounds(20, 265, BTN_W, BTN_H);
        btnExcluir.setBounds(160, 265, BTN_W, BTN_H);
        btnConsultar.setBounds(300, 265, BTN_W, BTN_H);
        btnListarTodos.setBounds(440, 265, BTN_W, BTN_H);

        painel.add(btnAgendar);
        painel.add(btnExcluir);
        painel.add(btnConsultar);
        painel.add(btnListarTodos);

        // Botão voltar
        btnVoltar = criarBotao("← Voltar", COR_AZUL_MEDIO, COR_BRANCO);

        btnVoltar.setBounds(20, 305, BTN_W, BTN_H);

        painel.add(btnVoltar);

        // ── Área de exibição ──────────────────────────

        areaExibicao = new JTextArea();

        areaExibicao.setEditable(false);

        areaExibicao.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        areaExibicao.setForeground(COR_FUNDO);

        areaExibicao.setBackground(new Color(240, 250, 255));

        areaExibicao.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));

        // JScrollPane -> adiciona barra de rolagem
        JScrollPane scroll = new JScrollPane(areaExibicao);

        scroll.setBounds(20, 350, 645, 165);

        scroll.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));

        painel.add(scroll);

        // Eventos dos componentes
        btnAgendar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnListarTodos.addActionListener(this);
        btnVoltar.addActionListener(this);

        // Evento para detectar troca de animal selecionado
        cmbAnimal.addActionListener(this);

        // Preenche automaticamente o nome do dono
        preencherDono();

        // Exibe a janela
        setVisible(true);
    }

    // Método auxiliar para criar campos de texto padronizados
    private JTextField criarCampo(int y) {

        JTextField f = new JTextField();

        f.setBounds(TXT_X, y, TXT_W, LBL_H);

        f.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        f.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));

        return f;
    }

    // Método auxiliar para criar botões padronizados
    private JButton criarBotao(String texto, Color bg, Color fg) {

        JButton btn = new JButton(texto);

        btn.setBackground(bg);

        btn.setForeground(fg);

        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));

        btn.setFocusPainted(false);

        return btn;
    }

    // Método executado ao clicar em algum botão
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == cmbAnimal)
            preencherDono();

        if (e.getSource() == btnAgendar)
            agendarServico();

        if (e.getSource() == btnExcluir)
            excluirAgendamento();

        if (e.getSource() == btnConsultar)
            consultarAgendamento();

        if (e.getSource() == btnListarTodos)
            listarTodos();

        // Volta para tela principal
        if (e.getSource() == btnVoltar) {

            dispose();

            new TelaPrincipal();
        }
    }

    // Preenche automaticamente o nome do dono do animal selecionado
    private void preencherDono() {

        int indice = cmbAnimal.getSelectedIndex();

        // Verifica se o índice é válido
        if (indice >= 0 && indice < listaAnimais.size()) {

            txtDono.setText(
                listaAnimais.get(indice).getDono().getNome()
            );

        } else {

            txtDono.setText("");
        }
    }

    // Método responsável por realizar o agendamento
    private void agendarServico() {

        int indice = cmbAnimal.getSelectedIndex();

        // Verifica se existe animal disponível
        if (indice < 0 || listaAnimais.isEmpty()) {

            areaExibicao.setText(
                "Nenhum animal disponível para agendar."
            );

            return;
        }

        String data = txtData.getText().trim();

        String horario = txtHorario.getText().trim();

        // Verifica campos vazios
        if (data.isEmpty() || horario.isEmpty()) {

            areaExibicao.setText(
                "Preencha a data e o horário."
            );

            return;
        }

        // Obtém animal selecionado
        Animal animalSelecionado = listaAnimais.get(indice);

        // Obtém serviço selecionado
        String tipoServico = (String) cmbServico.getSelectedItem();

        // Cria objeto Serviço
        Servico servico = new Servico(tipoServico, 0.0, "");

        // Cria objeto Agendamento
        Agendamento agendamento = new Agendamento(
            animalSelecionado,
            servico,
            data,
            horario,
            "Agendado"
        );

        // Adiciona na lista
        listaAgendamentos.add(agendamento);

        // Exibe mensagem de sucesso
        areaExibicao.setText(
            "Agendamento realizado com sucesso!\n" +
            "Animal: " + animalSelecionado.getNome() +
            " | Serviço: " + tipoServico +
            " | Data: " + data + " às " + horario
        );

        limparCampos();
    }

    // Exclui um agendamento pelo nome do animal
    private void excluirAgendamento() {

        String nomeAnimal =
            (String) cmbAnimal.getSelectedItem();

        for (int i = 0; i < listaAgendamentos.size(); i++) {

            if (listaAgendamentos.get(i)
                .getAnimal()
                .getNome()
                .equalsIgnoreCase(nomeAnimal)) {

                listaAgendamentos.remove(i);

                areaExibicao.setText(
                    "Agendamento de '" + nomeAnimal +
                    "' excluído com sucesso!"
                );

                return;
            }
        }

        areaExibicao.setText(
            "Nenhum agendamento encontrado para '" +
            nomeAnimal + "'."
        );
    }

    // Consulta agendamentos de um animal
    private void consultarAgendamento() {

        String nomeAnimal =
            (String) cmbAnimal.getSelectedItem();

        StringBuilder resultado = new StringBuilder();

        // Percorre todos os agendamentos
        for (Agendamento ag : listaAgendamentos) {

            if (ag.getAnimal().getNome()
                .equalsIgnoreCase(nomeAnimal)) {

                resultado.append("Animal: ")
                        .append(ag.getAnimal().getNome())
                        .append(" | Serviço: ")
                        .append(ag.getServico().getTipo())
                        .append(" | Data: ")
                        .append(ag.getData())
                        .append(" às ")
                        .append(ag.getHorario())
                        .append(" | Status: ")
                        .append(ag.getStatus())
                        .append("\n");
            }
        }

        // Operador ternário
        areaExibicao.setText(
            resultado.length() == 0
            ? "Nenhum agendamento encontrado para '" + nomeAnimal + "'."
            : resultado.toString()
        );
    }

    // Lista todos os agendamentos cadastrados
    private void listarTodos() {

        if (listaAgendamentos.isEmpty()) {

            areaExibicao.setText(
                "Nenhum agendamento cadastrado."
            );

            return;
        }

        StringBuilder lista =
            new StringBuilder("=== Agendamentos ===\n");

        for (Agendamento ag : listaAgendamentos) {

            lista.append("Animal: ")
                 .append(ag.getAnimal().getNome())
                 .append(" | Dono: ")
                 .append(ag.getAnimal().getDono().getNome())
                 .append(" | Serviço: ")
                 .append(ag.getServico().getTipo())
                 .append(" | Data: ")
                 .append(ag.getData())
                 .append(" às ")
                 .append(ag.getHorario())
                 .append(" | Status: ")
                 .append(ag.getStatus())
                 .append("\n");
        }

        areaExibicao.setText(lista.toString());
    }

    // Limpa os campos de data e horário
    private void limparCampos() {

        txtData.setText("");

        txtHorario.setText("");
    }
}