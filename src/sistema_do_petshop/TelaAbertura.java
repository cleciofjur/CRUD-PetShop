package sistema_do_petshop;

import javax.swing.*;
import java.awt.Color; // Classe que permite manipular cores
import java.awt.Font; // Classe de manipulação de fontes
import java.awt.GraphicsEnvironment; // Classe de acesso aos recursos gráficos
import java.awt.event.ActionEvent; // Classe que representa um evento quando é feito alguma interação com o sistema
import java.awt.event.ActionListener; // Classe que "ouve" e trata os eventos como cliques
import java.util.Arrays;
import java.util.List;

// Uma chamada cria uma interface capaz de interagir com as respostas do usuário
public class TelaAbertura implements ActionListener {

    JFrame tela = new JFrame("Pet Shop Paraíso dos Pets");
    JPanel painel = new JPanel();
    JButton btsalvar = new JButton("Entrar");
    JButton btexibir = new JButton("Sair");

    JLabel lbtitulo    = new JLabel("🐾 Pet Shop Paraíso dos Pets");
    JLabel lbsubtitulo = new JLabel("Porque seu pet merece o melhor!");
    JLabel lblBanho    = new JLabel("🛁  Banho");
    JLabel lblTosa     = new JLabel("✂  Tosa");
    JLabel lblConsulta = new JLabel("🩺  Consulta Veterinária");

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

    // Método que cria a tela 
    void criarTela() {

        tela.setSize(480, 420);
        tela.setLocation(500, 250);
        
        // Define que ao clicar no X todo o programa é encerrado, não somente oculto
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Define o posicionamento absoluto
        painel.setLayout(null);
        painel.setBackground(new Color(5, 63, 92));

        lbtitulo.setBounds(60, 30, 380, 40);
        lbtitulo.setFont(getFonteComEmoji(Font.BOLD, 20));
        lbtitulo.setForeground(new Color(247, 173, 25));

        lbsubtitulo.setBounds(100, 72, 300, 25);
        lbsubtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbsubtitulo.setForeground(new Color(159, 231, 245));

        lblBanho.setBounds(150, 150, 220, 30);
        lblBanho.setFont(getFonteComEmoji(Font.BOLD, 15));
        lblBanho.setForeground(Color.WHITE);

        lblTosa.setBounds(150, 195, 220, 30);
        lblTosa.setFont(getFonteComEmoji(Font.BOLD, 15));
        lblTosa.setForeground(Color.WHITE);

        lblConsulta.setBounds(150, 240, 220, 30);
        lblConsulta.setFont(getFonteComEmoji(Font.BOLD, 15)); 
        lblConsulta.setForeground(Color.WHITE);

        btsalvar.setBounds(120, 320, 100, 30);
        btsalvar.setBackground(new Color(247, 173, 25));
        btsalvar.setForeground(new Color(5, 63, 92));
        btsalvar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btsalvar.setFocusPainted(false);

        btexibir.setBounds(240, 320, 100, 30);
        btexibir.setBackground(new Color(66, 158, 189));
        btexibir.setForeground(Color.WHITE);
        btexibir.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btexibir.setFocusPainted(false);

        // Identifica que a própria classe é responsavél por executar a ação
        btsalvar.addActionListener(this);
        btexibir.addActionListener(this);

        painel.add(lbtitulo);
        painel.add(lbsubtitulo);
        painel.add(lblBanho);
        painel.add(lblTosa);
        painel.add(lblConsulta);
        painel.add(btsalvar);
        painel.add(btexibir);

        // Adicione o painel com os componentes
        tela.getContentPane().add(painel);
        tela.setVisible(true);
    }

    
    public static void main(String[] args) {
    	// Cria um objeto da classe, alocando espaço na memória
        TelaAbertura tela = new TelaAbertura();
        
        // Chama o método que pertence ao objeto criado, carregando todos os componentes
        tela.criarTela();
    }

    @Override
    
    // parametro e carrega as info quando um botão é clicado
    public void actionPerformed(ActionEvent e) {
    	
    	// Retorna o objeto que o botão disparou
        if (e.getSource() == btsalvar) {
        	
        	// Cria um usuário fixo para logar no sistema
            Usuario usuario = new Usuario("admin", "123", "admin");
            
            // Crie a tela de login passando o usuário como parametro, quem validar
            TelaLogin telaLogin = new TelaLogin(usuario);
            telaLogin.setVisible(true);
            tela.setVisible(false);
        }
        if (e.getSource() == btexibir) {
            System.exit(0);
        }
    }
}