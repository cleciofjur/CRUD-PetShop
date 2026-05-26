package sistema_do_petshop;

import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;
import javax.swing.*;
import javax.swing.border.*;

// Meio onde a tela de login já nasce "herdando" uma janela de JFramen e é capaz de implementar uma interface
public class TelaLogin extends JFrame implements ActionListener {

	// Não existe efeito real, mas caso houvesse, seria para fazer uma comparação de series para rodar o sistema
	// Necessário por causa do extends JFrame
    private static final long serialVersionUID = 1L;

    JLabel lblUsuario, lblSenha, lblMensagem, lblTitulo, lblPata;
    JTextField txtUsuario;
    JPasswordField txtSenha;
    JButton btnEntrar, btnLimpar;

    // Classe usada para validar um usuário, vai guardar um objeto dp tipo usuario
    Usuario usuarioValido;

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

    // Um construtor com parametro para que seja possivel executar a tela de login
    public TelaLogin(Usuario usuarioValido) {

        this.usuarioValido = usuarioValido;

        setTitle("Login - Pet Shop");
        setSize(440, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(new Color(5, 63, 92));

        lblPata = new JLabel("🐾 Pet Shop", SwingConstants.CENTER);
        lblPata.setBounds(0, 20, 440, 35);
        lblPata.setFont(getFonteComEmoji(Font.BOLD, 22));  // ← fonte com emoji
        lblPata.setForeground(new Color(247, 173, 25));
        add(lblPata);

        lblTitulo = new JLabel("Sistema de Gestão", SwingConstants.CENTER);
        lblTitulo.setBounds(0, 55, 440, 20);
        lblTitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblTitulo.setForeground(new Color(159, 231, 245));
        add(lblTitulo);

        lblUsuario = new JLabel("Usuário:");
        lblUsuario.setBounds(90, 120, 80, 25);
        lblUsuario.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblUsuario.setForeground(Color.WHITE);
        add(lblUsuario);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(180, 120, 170, 28);
        txtUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtUsuario.setBorder(new LineBorder(new Color(66, 158, 189), 1));
        add(txtUsuario);

        lblSenha = new JLabel("Senha:");
        lblSenha.setBounds(90, 170, 80, 25);
        lblSenha.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblSenha.setForeground(Color.WHITE);
        add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(180, 170, 170, 28);
        txtSenha.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        txtSenha.setBorder(new LineBorder(new Color(66, 158, 189), 1));
        add(txtSenha);

        lblMensagem = new JLabel("", SwingConstants.CENTER);
        lblMensagem.setBounds(80, 215, 280, 20);
        lblMensagem.setFont(getFonteComEmoji(Font.PLAIN, 11));  // ← fonte com emoji (⚠)
        lblMensagem.setForeground(new Color(230, 57, 70));
        add(lblMensagem);

        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(110, 290, 100, 30);
        btnEntrar.setBackground(new Color(247, 173, 25));
        btnEntrar.setForeground(new Color(5, 63, 92));
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEntrar.setFocusPainted(false);
        add(btnEntrar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(225, 290, 100, 30);
        btnLimpar.setBackground(new Color(66, 158, 189));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLimpar.setFocusPainted(false);
        add(btnLimpar);

        btnEntrar.addActionListener(this);
        btnLimpar.addActionListener(this);
        // Descarta o uso de mouse, o usuário pode fazer tudo pelo teclado
        getRootPane().setDefaultButton(btnEntrar);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEntrar) {
            validarLogin();
        }
        if (e.getSource() == btnLimpar) {
            txtUsuario.setText("");
            txtSenha.setText("");
            lblMensagem.setText("");
            txtUsuario.requestFocus();
        }
    }

    private void validarLogin() {
        String loginDigitado = txtUsuario.getText();
        String senhaDigitada = new String(txtSenha.getPassword());

        if (usuarioValido.autenticar(loginDigitado, senhaDigitada)) {
            dispose(); 				// fecha a tela de login
            new TelaPrincipal(); 	// abre a tela do sistema
        } else {
            lblMensagem.setText("⚠  Usuário ou senha incorretos!");
        }
    }
}