package sistema_do_petshop;

// Importa componentes gráficos do Swing
import javax.swing.*;

// Importa classes para cores e fontes
import java.awt.Color;
import java.awt.Font;

// Importa classes para tratamento de eventos
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Classe principal da tela de abertura
// Implementa ActionListener para capturar eventos dos botões
public class TelaAbertura implements ActionListener {

    // Criação da janela principal
    JFrame tela = new JFrame("Pet Shop Paraíso dos Pets");

    // Painel principal da interface
    JPanel painel = new JPanel();

    // Botão para entrar no sistema
    JButton btsalvar = new JButton("Entrar");

    // Botão para sair do sistema
    JButton btexibir = new JButton("Sair");

    // Labels de textos da interface
    JLabel lbtitulo = new JLabel("🐾 Pet Shop Paraíso dos Pets");

    JLabel lbsubtitulo = new JLabel("Porque seu pet merece o melhor!");

    JLabel lblBanho = new JLabel("🛁  Banho");

    JLabel lblTosa = new JLabel("✂  Tosa");

    JLabel lblConsulta = new JLabel("🩺  Consulta Veterinária");

    // Método responsável por criar a interface
    void criarTela() {

        // Define tamanho da janela
        tela.setSize(480, 420);

        // Define posição da janela na tela
        tela.setLocation(500, 250);

        // Encerra o programa ao fechar a janela
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout nulo -> posicionamento manual dos componentes
        painel.setLayout(null);

        // Define cor de fundo do painel
        painel.setBackground(new Color(5, 63, 92));

        // ── Configuração do título ───────────────────

        lbtitulo.setBounds(60, 30, 380, 40);

        lbtitulo.setFont(new Font("Segoe UI", Font.BOLD, 20));

        lbtitulo.setForeground(new Color(247, 173, 25));

        // ── Configuração do subtítulo ────────────────

        lbsubtitulo.setBounds(100, 72, 300, 25);

        lbsubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        lbsubtitulo.setForeground(new Color(159, 231, 245));

        // ── Labels dos serviços ──────────────────────

        lblBanho.setBounds(150, 150, 220, 30);

        lblBanho.setFont(new Font("Segoe UI", Font.BOLD, 15));

        lblBanho.setForeground(Color.WHITE);

        lblTosa.setBounds(150, 195, 220, 30);

        lblTosa.setFont(new Font("Segoe UI", Font.BOLD, 15));

        lblTosa.setForeground(Color.WHITE);

        lblConsulta.setBounds(150, 240, 220, 30);

        lblConsulta.setFont(new Font("Segoe UI", Font.BOLD, 15));

        lblConsulta.setForeground(Color.WHITE);

        // ── Configuração do botão Entrar ─────────────

        btsalvar.setBounds(120, 320, 100, 30);

        btsalvar.setBackground(new Color(247, 173, 25));

        btsalvar.setForeground(new Color(5, 63, 92));

        btsalvar.setFont(new Font("Segoe UI", Font.BOLD, 13));

        // Remove o efeito de foco ao clicar
        btsalvar.setFocusPainted(false);

        // ── Configuração do botão Sair ───────────────

        btexibir.setBounds(240, 320, 100, 30);

        btexibir.setBackground(new Color(66, 158, 189));

        btexibir.setForeground(Color.WHITE);

        btexibir.setFont(new Font("Segoe UI", Font.BOLD, 13));

        btexibir.setFocusPainted(false);

        // Adiciona eventos de clique aos botões
        btsalvar.addActionListener(this);

        btexibir.addActionListener(this);

        // Adiciona componentes ao painel
        painel.add(lbtitulo);

        painel.add(lbsubtitulo);

        painel.add(lblBanho);

        painel.add(lblTosa);

        painel.add(lblConsulta);

        painel.add(btsalvar);

        painel.add(btexibir);

        // Adiciona o painel na janela
        tela.getContentPane().add(painel);

        // Exibe a janela
        tela.setVisible(true);
    }

    // Método principal do programa
    // main -> ponto de entrada da aplicação Java
    public static void main(String[] args) {

        // Cria objeto da tela
        TelaAbertura tela = new TelaAbertura();

        // Chama método para construir a interface
        tela.criarTela();
    }

    // Método executado quando algum botão é clicado
    @Override
    public void actionPerformed(ActionEvent e) {

        // Verifica se o botão clicado foi "Entrar"
        if (e.getSource() == btsalvar) {

            // Cria usuário padrão do sistema
            Usuario usuario = new Usuario("admin", "123", "admin");

            // Abre tela de login
            TelaLogin telaLogin = new TelaLogin(usuario);

            telaLogin.setVisible(true);

            // Oculta tela atual
            tela.setVisible(false);
        }

        // Verifica se o botão clicado foi "Sair"
        if (e.getSource() == btexibir) {

            // Encerra completamente o programa
            System.exit(0);
        }
    }
}