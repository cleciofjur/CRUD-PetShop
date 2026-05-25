package sistema_do_petshop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class TelaPrincipal extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    private static final Color COR_FUNDO      = new Color(5, 63, 92);
    private static final Color COR_AZUL_MEDIO = new Color(66, 158, 189);
    private static final Color COR_AMBAR      = new Color(247, 173, 25);
    private static final Color COR_AZUL_CLARO = new Color(159, 231, 245);
    private static final Color COR_BRANCO     = Color.WHITE;

    JLabel lblNome, lblEspecie, lblRaca, lblDono, lblTelefone;

    JTextField txtNome, txtEspecie, txtRaca, txtDono, txtTelefone;
    JTextArea areaExibicao;

    // MUDANÇA 1: Removidas as variáveis btnAlterarCor e btnAlterarFonte
    JButton btnAdicionar, btnExcluir, btnConsultar;
    JButton btnLimpar, btnAgendamento;

    ArrayList<Animal> listaAnimais;

    private static final int LBL_X = 20;
    private static final int LBL_W = 130;
    private static final int LBL_H = 25;
    private static final int TXT_X = 160;
    private static final int TXT_W = 190;
    private static final int BTN_H = 32;

    public TelaPrincipal() {

        listaAnimais = new ArrayList<>();

        setTitle("Sistema Pet Shop - Cadastro de Animais");
        setSize(700, 560);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel painel = new JPanel();
        painel.setLayout(null);
        painel.setBackground(COR_FUNDO);
        setContentPane(painel);

        JLabel lblTitulo = new JLabel("🐾  Cadastro de Animais");
        lblTitulo.setBounds(20, 15, 400, 30);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblTitulo.setForeground(COR_AMBAR);
        painel.add(lblTitulo);

        JLabel lblSub = new JLabel("Preencha os dados do animal e do responsável");
        lblSub.setBounds(20, 45, 400, 18);
        lblSub.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblSub.setForeground(COR_AZUL_CLARO);
        painel.add(lblSub);

        int[] linhasY = {80, 115, 150, 185, 220};
        String[] nomes = {"Nome do animal:", "Espécie:", "Raça:", "Nome do dono:", "Telefone:"};
        JLabel[] labels = new JLabel[5];

        for (int i = 0; i < labels.length; i++) {
            labels[i] = new JLabel(nomes[i]);
            labels[i].setBounds(LBL_X, linhasY[i], LBL_W, LBL_H);
            labels[i].setFont(new Font("Segoe UI", Font.BOLD, 13));
            labels[i].setForeground(COR_BRANCO);
            painel.add(labels[i]);
        }

        lblNome     = labels[0];
        lblEspecie  = labels[1];
        lblRaca     = labels[2];
        lblDono     = labels[3];
        lblTelefone = labels[4];

        txtNome     = criarCampo(linhasY[0]);
        txtEspecie  = criarCampo(linhasY[1]);
        txtRaca     = criarCampo(linhasY[2]);
        txtDono     = criarCampo(linhasY[3]);
        txtTelefone = criarCampo(linhasY[4]);

        painel.add(txtNome);
        painel.add(txtEspecie);
        painel.add(txtRaca);
        painel.add(txtDono);
        painel.add(txtTelefone);

        // MUDANÇA 2: Botões reorganizados em duas fileiras centralizadas
        // Fileira 1: Adicionar | Excluir | Consultar  (juntos, mesma largura)
        // Fileira 2: Limpar Campos | Agendamento      (centralizados abaixo)

        // Largura de cada botão da fileira 1 para caber os 3 juntos
        int btnLargura1 = 190;
        // Posição X do primeiro botão para centralizar os 3 na janela (700px)
        // Total ocupado: 3 * 190 + 2 * 10 (espaço entre eles) = 590
        // Margem lateral: (700 - 590) / 2 = 55
        int xInicio1 = 55;
        int espacamento = 10;

        btnAdicionar = criarBotao("Adicionar", COR_AMBAR, COR_FUNDO);
        btnExcluir   = criarBotao("Excluir", COR_AZUL_MEDIO, COR_BRANCO);
        btnConsultar = criarBotao("Consultar", COR_AZUL_MEDIO, COR_BRANCO);

        btnAdicionar.setBounds(xInicio1, 265, btnLargura1, BTN_H);
        btnExcluir.setBounds(xInicio1 + btnLargura1 + espacamento, 265, btnLargura1, BTN_H);
        btnConsultar.setBounds(xInicio1 + (btnLargura1 + espacamento) * 2, 265, btnLargura1, BTN_H);

        painel.add(btnAdicionar);
        painel.add(btnExcluir);
        painel.add(btnConsultar);

        // Largura dos botões da fileira 2
        int btnLargura2 = 200;
        // Total ocupado: 2 * 200 + 1 * 10 = 410
        // Margem lateral: (700 - 410) / 2 = 145
        int xInicio2 = 145;

        btnLimpar     = criarBotao("Limpar Campos", COR_AZUL_MEDIO, COR_BRANCO);
        btnAgendamento = criarBotao("Agendamento →", COR_AMBAR, COR_FUNDO);

        btnLimpar.setBounds(xInicio2, 308, btnLargura2, BTN_H);
        btnAgendamento.setBounds(xInicio2 + btnLargura2 + espacamento, 308, btnLargura2, BTN_H);

        painel.add(btnLimpar);
        painel.add(btnAgendamento);

        areaExibicao = new JTextArea();
        areaExibicao.setEditable(false);
        areaExibicao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaExibicao.setForeground(COR_FUNDO);
        areaExibicao.setBackground(new Color(240, 250, 255));
        areaExibicao.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));

        JScrollPane scroll = new JScrollPane(areaExibicao);
        scroll.setBounds(20, 355, 645, 155);
        scroll.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        painel.add(scroll);

        btnAdicionar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnAgendamento.addActionListener(this);

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

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionar)  adicionarAnimal();
        if (e.getSource() == btnExcluir)    excluirAnimal();
        if (e.getSource() == btnConsultar)  consultarAnimal();
        if (e.getSource() == btnLimpar)     limparCampos();

        if (e.getSource() == btnAgendamento) {
            dispose();
            new TelaAgendamento(listaAnimais);
        }

        // MUDANÇA 1: Removidos os blocos de btnAlterarCor e btnAlterarFonte
    }

    private void adicionarAnimal() {
        String nome     = txtNome.getText().trim();
        String especie  = txtEspecie.getText().trim();
        String raca     = txtRaca.getText().trim();
        String nomeDono = txtDono.getText().trim();
        String tel      = txtTelefone.getText().trim();

        if (nome.isEmpty() || nomeDono.isEmpty()) {
            areaExibicao.setText("Preencha com o nome do animal e do dono!");
            return;
        }

        Cliente dono   = new Cliente(nomeDono, tel, "");
        Animal animal  = new Animal(nome, especie, raca, dono);
        listaAnimais.add(animal);

        areaExibicao.setText(
            "Animal adicionado com sucesso!\nNome: " + nome + " | Dono: " + nomeDono
        );

        limparCampos();
    }

    private void excluirAnimal() {
        String nome = txtNome.getText().trim();

        for (int i = 0; i < listaAnimais.size(); i++) {
            if (listaAnimais.get(i).getNome().equalsIgnoreCase(nome)) {
                listaAnimais.remove(i);
                areaExibicao.setText("Animal " + nome + " excluído com sucesso!");
                limparCampos();
                return;
            }
        }

        areaExibicao.setText("Animal " + nome + " não encontrado.");
    }

    // MUDANÇA 3: Consultar agora exibe TODOS os animais cadastrados
    // Cada animal aparece em um bloco separado por uma linha "---"
    public void consultarAnimal() {

        // Verifica se a lista está vazia antes de tentar exibir
        if (listaAnimais.isEmpty()) {
            areaExibicao.setText("Nenhum animal cadastrado ainda.");
            return;
        }

        StringBuilder resultado = new StringBuilder();

        // Percorre todos os animais da lista, um por um
        for (int i = 0; i < listaAnimais.size(); i++) {

            Animal a = listaAnimais.get(i);

            // Cabeçalho numerado para cada animal
            resultado.append("Animal ").append(i + 1).append(":\n");
            resultado.append("  Nome     : ").append(a.getNome()).append("\n");
            resultado.append("  Espécie  : ").append(a.getEspecie()).append("\n");
            resultado.append("  Raça     : ").append(a.getRaca()).append("\n");
            resultado.append("  Dono     : ").append(a.getDono().getNome()).append("\n");
            resultado.append("  Telefone : ").append(a.getDono().getTel()).append("\n");

            // Linha separadora entre os blocos (exceto no último)
            if (i < listaAnimais.size() - 1) {
                resultado.append("  -----------------------------------\n");
            }
        }

        areaExibicao.setText(resultado.toString());
    }

    private void limparCampos() {
        txtNome.setText("");
        txtEspecie.setText("");
        txtRaca.setText("");
        txtDono.setText("");
        txtTelefone.setText("");
    }

    // MUDANÇA 1: Métodos alterarCor() e alterarFonte() foram completamente removidos
}