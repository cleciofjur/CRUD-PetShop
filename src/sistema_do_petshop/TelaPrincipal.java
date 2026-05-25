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

// Declaração da classe TelaPrincipal
// Herda JFrame, tornando-se uma janela gráfica
// Implementa ActionListener para capturar eventos dos botões
public class TelaPrincipal extends JFrame implements ActionListener {

    // serialVersionUID utilizado para controle de serialização da classe
    private static final long serialVersionUID = 1L;

    // static final -> constantes que não podem ser alteradas
    private static final Color COR_FUNDO      = new Color(5, 63, 92);
    private static final Color COR_AZUL_MEDIO = new Color(66, 158, 189);
    private static final Color COR_AMBAR      = new Color(247, 173, 25);
    private static final Color COR_AZUL_CLARO = new Color(159, 231, 245);
    private static final Color COR_BRANCO     = Color.WHITE;

    JLabel lblNome, lblEspecie, lblRaca, lblDono, lblTelefone;

    JTextField txtNome, txtEspecie, txtRaca, txtDono, txtTelefone;
    JTextArea areaExibicao;
    JButton btnAdicionar, btnExcluir, btnConsultar;
    JButton btnLimpar, btnAlterarCor, btnAlterarFonte, btnAgendamento;

    // ArrayList -> lista dinâmica usada para armazenar os animais cadastrados
    ArrayList<Animal> listaAnimais;

    // Constantes de posicionamento e tamanho dos componentes
    // Facilita padronização da interface
    private static final int LBL_X  = 20;
    private static final int LBL_W  = 130;
    private static final int LBL_H  = 25;
    private static final int TXT_X  = 160;
    private static final int TXT_W  = 190;
    private static final int BTN_W  = 130;
    private static final int BTN_H  = 30;

    // Método construtor da classe
    // Executado automaticamente ao criar um objeto TelaPrincipal
    public TelaPrincipal() {

        // Inicializa a lista de animais vazia
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

        // Vetor contendo as posições Y das linhas
        int[] linhasY = {80, 115, 150, 185, 220};

        // Vetor contendo os textos dos labels
        String[] nomes = {
            "Nome do animal:",
            "Espécie:",
            "Raça:",
            "Nome do dono:",
            "Telefone:"
        };

        // Vetor para armazenar os labels
        JLabel[] labels = new JLabel[5];

        // Estrutura de repetição FOR
        // Cria automaticamente todos os labels
        for (int i = 0; i < labels.length; i++) {

            // Cria um JLabel usando o texto correspondente
            labels[i] = new JLabel(nomes[i]);

            // Define posição e tamanho
            labels[i].setBounds(LBL_X, linhasY[i], LBL_W, LBL_H);

            // Define fonte
            labels[i].setFont(new Font("Segoe UI", Font.BOLD, 13));

            // Define cor
            labels[i].setForeground(COR_BRANCO);

            // Adiciona ao painel
            painel.add(labels[i]);
        }

        // Associa os labels do vetor às variáveis da classe
        lblNome     = labels[0];
        lblEspecie  = labels[1];
        lblRaca     = labels[2];
        lblDono     = labels[3];
        lblTelefone = labels[4];

        // Cria os campos de texto usando método auxiliar
        txtNome     = criarCampo(linhasY[0]);
        txtEspecie  = criarCampo(linhasY[1]);
        txtRaca     = criarCampo(linhasY[2]);
        txtDono     = criarCampo(linhasY[3]);
        txtTelefone = criarCampo(linhasY[4]);

        // Adiciona os campos ao painel
        painel.add(txtNome);
        painel.add(txtEspecie);
        painel.add(txtRaca);
        painel.add(txtDono);
        painel.add(txtTelefone);

        btnAdicionar = criarBotao("Adicionar", COR_AMBAR, COR_FUNDO);
        btnExcluir   = criarBotao("Excluir", COR_AZUL_MEDIO, COR_BRANCO);
        btnConsultar = criarBotao("Consultar", COR_AZUL_MEDIO, COR_BRANCO);
        
        btnAdicionar.setBounds(20, 265, BTN_W, BTN_H);
        btnExcluir.setBounds(160, 265, BTN_W, BTN_H);
        btnConsultar.setBounds(300, 265, BTN_W, BTN_H);
        
        painel.add(btnAdicionar);
        painel.add(btnExcluir);
        painel.add(btnConsultar);
        
        btnLimpar = criarBotao("Limpar Campos", COR_AZUL_MEDIO, COR_BRANCO);
        btnAlterarCor = criarBotao("Alterar Cor", COR_AZUL_MEDIO, COR_BRANCO);
        btnAlterarFonte = criarBotao("Alterar Fonte", COR_AZUL_MEDIO, COR_BRANCO);
        btnAgendamento = criarBotao("Agendamento →", COR_AMBAR, COR_FUNDO);
        
        btnLimpar.setBounds(20, 305, BTN_W, BTN_H);
        btnAlterarCor.setBounds(160, 305, BTN_W, BTN_H);
        btnAlterarFonte.setBounds(300, 305, BTN_W, BTN_H);
        btnAgendamento.setBounds(440, 305, BTN_W, BTN_H);
        
        painel.add(btnLimpar);
        painel.add(btnAlterarCor);
        painel.add(btnAlterarFonte);
        painel.add(btnAgendamento);
        
        areaExibicao = new JTextArea();
        areaExibicao.setEditable(false);
        
        areaExibicao.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        areaExibicao.setForeground(COR_FUNDO);
        areaExibicao.setBackground(new Color(240, 250, 255));
        areaExibicao.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        
        JScrollPane scroll = new JScrollPane(areaExibicao);
        scroll.setBounds(20, 350, 645, 160);
        scroll.setBorder(new LineBorder(COR_AZUL_MEDIO, 1));
        painel.add(scroll);

        btnAdicionar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnAlterarCor.addActionListener(this);
        btnAlterarFonte.addActionListener(this);
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

    // Método auxiliar para criar botões padronizados
    private JButton criarBotao(String texto, Color bg, Color fg) {
        JButton btn = new JButton(texto);
        btn.setBackground(bg);
        btn.setForeground(fg);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btn.setFocusPainted(false);
        return btn;
    }

    // Método executado quando um botão é clicado
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdicionar) {
        	adicionarAnimal();
        	}
            
        if (e.getSource() == btnExcluir) {
        	excluirAnimal();
        }

        if (e.getSource() == btnConsultar) {
        	consultarAnimal();
        }

        if (e.getSource() == btnLimpar) {
        	limparCampos();
        }
            
        if (e.getSource() == btnAlterarCor) {
        	alterarCor();
        }
            
        if (e.getSource() == btnAlterarFonte) {
        	alterarFonte();
        }
        
        if (e.getSource() == btnAgendamento) {
            dispose();
            new TelaAgendamento(listaAnimais);
        }
    }

    // Método responsável por adicionar um animal na lista
    private void adicionarAnimal() {

        String nome     = txtNome.getText().trim();
        String especie  = txtEspecie.getText().trim();
        String raca     = txtRaca.getText().trim();
        String nomeDono = txtDono.getText().trim();
        String tel      = txtTelefone.getText().trim();

        // isEmpty() verifica se o campo está vazio
        if (nome.isEmpty() || nomeDono.isEmpty()) {

            areaExibicao.setText("Preencha com o nome do animal e do dono!");
            return;
        }

        Cliente dono = new Cliente(nomeDono, tel, "");

        Animal animal = new Animal(nome, especie, raca, dono);
        listaAnimais.add(animal);

        areaExibicao.setText(
            "Animal adicionado com sucesso!\nNome: "
            + nome + " | Dono: " + nomeDono
        );

        limparCampos();
    }

    private void excluirAnimal() {

        String nome = txtNome.getText().trim();

        for (int i = 0; i < listaAnimais.size(); i++) {

            // equalsIgnoreCase() compara ignorando letras maiúsculas/minúsculas
            if (listaAnimais.get(i).getNome().equalsIgnoreCase(nome)) {

                listaAnimais.remove(i);

                areaExibicao.setText(
                    "Animal " + nome + " excluído com sucesso!"
                );

                limparCampos();

                return;
            }
        }

        // Caso não encontre o animal
        areaExibicao.setText("Animal " + nome + " não encontrado.");
    }

    // Método responsável por consultar animais
    public void consultarAnimal() {
        String busca = txtNome.getText().trim();

        // StringBuilder -> utilizado para montar textos dinamicamente
        // Mais eficiente que concatenação usando +
        StringBuilder resultado = new StringBuilder();

        // FOR EACH
        // Percorre todos os animais da lista
        for (Animal a : listaAnimais) {
            if (a.getNome().equalsIgnoreCase(busca) ||
                a.getDono().getNome().equalsIgnoreCase(busca)) {

                // append() adiciona texto ao StringBuilder
                resultado.append("Nome: ").append(a.getNome())
                         .append(" | Espécie: ").append(a.getEspecie())
                         .append(" | Raça: ").append(a.getRaca())
                         .append(" | Dono: ").append(a.getDono().getNome())
                         .append("\n");
            }
        }

        // Operador ternário:
        // condição ? valor_se_verdadeiro : valor_se_falso
        areaExibicao.setText(
            resultado.length() == 0
            ? "Nenhum animal encontrado."
            : resultado.toString()
        );
    }

    // Método responsável por limpar os campos
    private void limparCampos() {

        // Define todos os campos como texto vazio
        txtNome.setText("");
        txtEspecie.setText("");
        txtRaca.setText("");
        txtDono.setText("");
        txtTelefone.setText("");
    }

    // Método responsável por alterar cores da interface
    private void alterarCor() {

        // Altera cor do fundo da janela
        getContentPane().setBackground(new Color(173, 216, 230));

        // Altera cor da área de exibição
        areaExibicao.setBackground(new Color(240, 248, 255));
    }

    // Método responsável por alterar fontes da interface
    private void alterarFonte() {

        // Cria nova fonte
        Font fonteNova = new Font("Segoe UI", Font.BOLD, 14);

        // Aplica nova fonte
        areaExibicao.setFont(fonteNova);
        lblNome.setFont(fonteNova);
        lblDono.setFont(fonteNova);
    }
}