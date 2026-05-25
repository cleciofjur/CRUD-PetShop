package sistema_do_petshop;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

// Declaração da classe TelaLogin
// A classe herda JFrame, tornando-se uma janela gráfica
// Também implementa ActionListener para capturar eventos dos botões
public class TelaLogin extends JFrame implements ActionListener {

    // serialVersionUID é utilizado para controle de serialização da classe
    // Evita problemas de compatibilidade entre versões da classe
    private static final long serialVersionUID = 1L;

    JLabel lblUsuario, lblSenha, lblMensagem, lblTitulo, lblPata;
    JTextField txtUsuario;
    JPasswordField txtSenha;
    JButton btnEntrar, btnLimpar;

    // Objeto responsável por validar/autenticar o usuário
    Usuario usuarioValido;

    // Método construtor da classe TelaLogin
    // É executado automaticamente ao criar um objeto da classe
    public TelaLogin(Usuario usuarioValido) {

        // Armazena o objeto Usuario recebido no atributo da classe
        this.usuarioValido = usuarioValido;

        setTitle("Login - Pet Shop");
        setSize(440, 400);
        // Define que o programa será encerrado ao fechar a janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        // Define layout nulo
        setLayout(null);
        // Impede que o usuário redimensione a janela
        setResizable(false);
        getContentPane().setBackground(new Color(5, 63, 92));

        // Cria um JLabel com emoji e texto centralizado
        lblPata = new JLabel("🐾 Pet Shop", SwingConstants.CENTER);
        lblPata.setBounds(0, 20, 440, 35);
        lblPata.setFont(new Font("Segoe UI", Font.BOLD, 22));
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

        // Label inicialmente vazio
        // Será usado para exibir mensagens de erro
        lblMensagem = new JLabel("", SwingConstants.CENTER);

        lblMensagem.setBounds(80, 215, 280, 20);
        lblMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lblMensagem.setForeground(new Color(230, 57, 70));
        
        btnEntrar = new JButton("Entrar");
        btnEntrar.setBounds(110, 290, 100, 30);
        btnEntrar.setBackground(new Color(247, 173, 25));
        btnEntrar.setForeground(new Color(5, 63, 92));
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnEntrar.setFocusPainted(false);
        add(btnEntrar);

        // Cria botão "Limpar"
        btnLimpar = new JButton("Limpar");
        btnLimpar.setBounds(225, 290, 100, 30);
        btnLimpar.setBackground(new Color(66, 158, 189));
        btnLimpar.setForeground(Color.WHITE);
        btnLimpar.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btnLimpar.setFocusPainted(false);
        add(btnLimpar);

        btnEntrar.addActionListener(this);
        btnLimpar.addActionListener(this);
        getRootPane().setDefaultButton(btnEntrar);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // Verifica se o botão clicado foi o btnEntrar
        if (e.getSource() == btnEntrar) {
            // Chama o método responsável por validar o login
            validarLogin();
        }

        // Verifica se o botão clicado foi o btnLimpar
        if (e.getSource() == btnLimpar) {
            txtUsuario.setText("");
            txtSenha.setText("");
            lblMensagem.setText("");

            // Faz o foco voltar para o componente
            txtUsuario.requestFocus();
        }
    }

    private void validarLogin() {

        // Captura o texto digitado no campo usuário
        String loginDigitado = txtUsuario.getText();

        // Captura a senha digitada
        // getPassword() retorna um vetor de caracteres (char[])
        // new String(...) converte para String
        String senhaDigitada = new String(txtSenha.getPassword());

        // Chama o método autenticar da classe Usuario
        // Verifica se login e senha estão corretos
        if (usuarioValido.autenticar(loginDigitado, senhaDigitada)) {

            // Fecha a tela de login atual
            dispose();

            // Abre a tela principal do sistema
            new TelaPrincipal();

        } else {

            // Exibe mensagem de erro caso login ou senha estejam incorretos
            lblMensagem.setText("⚠  Usuário ou senha incorretos!");
        }
    }
}