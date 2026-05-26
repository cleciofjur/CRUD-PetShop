package sistema_do_petshop;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

    // Constantes das cores usadas no sistema
    private static final Color COR_FUNDO      = new Color(5, 63, 92);
    private static final Color COR_AZUL_MEDIO = new Color(66, 158, 189);
    private static final Color COR_AMBAR      = new Color(247, 173, 25);
    private static final Color COR_AZUL_CLARO = new Color(159, 231, 245);
    private static final Color COR_BRANCO     = Color.WHITE;

    JLabel lblNome, lblEspecie, lblRaca, lblDono, lblTelefone;

    JTextField txtNome, txtEspecie, txtRaca, txtDono, txtTelefone;
    JTextArea areaExibicao;

    JButton btnAdicionar, btnExcluir, btnConsultar;
    JButton btnLimpar, btnAgendamento;

    // Classe do Java que funciona como uma lista dinâmica
    ArrayList<Animal> listaAnimais;

    // Constantes de posicionamento e tamanho de componentes da interface
    private static final int LBL_X = 20;
    private static final int LBL_W = 130;
    private static final int LBL_H = 25;
    private static final int TXT_X = 160;
    private static final int TXT_W = 190;
    private static final int BTN_H = 32;

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

    public TelaPrincipal() {

    	// Prepara um espaço na memória para receber animais, até então esta vazia
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
        lblTitulo.setFont(getFonteComEmoji(Font.BOLD, 18));  // ← fonte com emoji
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

        // Usa o for para criar todos os label de uma só vez, ssem precisar fazer um a um
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

        // Medidas que armazenam o posicionamento dos botões para centralizar
        int btnLargura1 = 190;
        int xInicio1    = 55;
        int espacamento = 10;

        btnAdicionar = criarBotao("Adicionar", COR_AMBAR, COR_FUNDO);
        btnExcluir   = criarBotao("Excluir",   COR_AZUL_MEDIO, COR_BRANCO);
        btnConsultar = criarBotao("Consultar", COR_AZUL_MEDIO, COR_BRANCO);

        btnAdicionar.setBounds(xInicio1, 265, btnLargura1, BTN_H);
        btnExcluir.setBounds(xInicio1 + btnLargura1 + espacamento, 265, btnLargura1, BTN_H);
        btnConsultar.setBounds(xInicio1 + (btnLargura1 + espacamento) * 2, 265, btnLargura1, BTN_H);

        painel.add(btnAdicionar);
        painel.add(btnExcluir);
        painel.add(btnConsultar);

        int btnLargura2 = 200;
        int xInicio2    = 145;

        btnLimpar      = criarBotao("Limpar Campos", COR_AZUL_MEDIO, COR_BRANCO);
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

    // Métodos auxiliares que ao invés de reescrever os componentes, o método é chamado e padroniza tudo
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
            dispose(); // Fecha e tela principal
            new TelaAgendamento(listaAnimais); // abre a tela de agendamento, passando a lista de animais registrados como um parêmetro
        }
    }

    private void adicionarAnimal() {
    	// Captura o texto qur foi digitado e limpa o campo
        String nome     = txtNome.getText().trim(); //.trim() -> remove os espaços em branco antes e depois da String
        String especie  = txtEspecie.getText().trim();
        String raca     = txtRaca.getText().trim();
        String nomeDono = txtDono.getText().trim();
        String tel      = txtTelefone.getText().trim();

        if (nome.isEmpty() || nomeDono.isEmpty()) {
            areaExibicao.setText("Preencha com o nome do animal e do dono!");
            return;
        }

        Cliente dono  = new Cliente(nomeDono, tel, "");
        Animal animal = new Animal(nome, especie, raca, dono);
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

    public void consultarAnimal() {

        if (listaAnimais.isEmpty()) {
            areaExibicao.setText("Nenhum animal cadastrado ainda.");
            return;
        }

        // Permite que dentro ddo for seja contruído um texto
        StringBuilder resultado = new StringBuilder();

        for (int i = 0; i < listaAnimais.size(); i++) {
            Animal a = listaAnimais.get(i);
            
            // método do STringBuilder que adiciona conteúdo ao finla do texto
            resultado.append("Animal ").append(i + 1).append(":\n");
            resultado.append("  Nome     : ").append(a.getNome()).append("\n");
            resultado.append("  Espécie  : ").append(a.getEspecie()).append("\n");
            resultado.append("  Raça     : ").append(a.getRaca()).append("\n");
            resultado.append("  Dono     : ").append(a.getDono().getNome()).append("\n");
            resultado.append("  Telefone : ").append(a.getDono().getTel()).append("\n");

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
}