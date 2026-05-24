package sistema_do_petshop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaLogin extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JLabel lblUsuario, lblSenha, lblMensagem, lblImagem;
	JLabel lblIconePessoa, lblIconeCadeado;
	JTextField txtUsuario;
	JPasswordField txtSenha;
	JButton btnEntrar, btnLimpar;
	Usuario usuarioValido;

	public TelaLogin(Usuario usuarioValido) {
		this.usuarioValido = usuarioValido;

		setTitle("Login - Pet Shop");
		setSize(480, 420);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);

		// fundo azul igual tela de abertura
		getContentPane().setBackground(new Color(100, 180, 255));

		// imagem de usuário centralizada no topo
		ImageIcon icone = new ImageIcon("C:/Users/eluan/Downloads/CRUD-PetShop-main/CRUD-PetShop-main/src/Imagens/user-login-icon-png-1.png");
		Image img = icone.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		lblImagem = new JLabel(new ImageIcon(img));
		lblImagem.setBounds(200, 20, 70, 70);
		add(lblImagem);

		// label usuário
		lblUsuario = new JLabel("Usuário:");
		lblUsuario.setBounds(80, 160, 80, 25);
		lblUsuario.setFont(new Font("Arial", Font.BOLD, 14));
		lblUsuario.setForeground(Color.WHITE);
		add(lblUsuario);

		// campo usuário
		txtUsuario = new JTextField();
		txtUsuario.setBounds(210, 160, 150, 25);
		add(txtUsuario);

		// label senha
		lblSenha = new JLabel("Senha:");
		lblSenha.setBounds(80, 210, 80, 25);
		lblSenha.setFont(new Font("Arial", Font.BOLD, 14));
		lblSenha.setForeground(Color.WHITE);
		add(lblSenha);

		// campo senha
		txtSenha = new JPasswordField();
		txtSenha.setBounds(210, 210, 150, 25);
		add(txtSenha);

		// mensagem de erro
		lblMensagem = new JLabel("");
		lblMensagem.setBounds(120, 250, 280, 25);
		lblMensagem.setFont(new Font("Arial", Font.PLAIN, 12));
		lblMensagem.setForeground(Color.RED);
		add(lblMensagem);

		// botão entrar - azul escuro com letra branca
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(110, 320, 110, 30);
		btnEntrar.setBackground(new Color(30, 100, 180));
		btnEntrar.setForeground(Color.WHITE);
		btnEntrar.setFont(new Font("Arial", Font.BOLD, 14));
		btnEntrar.setFocusPainted(false);
		add(btnEntrar);

		// botão limpar - cinza com letra branca
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(240, 320, 110, 30);
		btnLimpar.setBackground(new Color(180, 180, 180));
		btnLimpar.setForeground(Color.WHITE);
		btnLimpar.setFont(new Font("Arial", Font.BOLD, 14));
		btnLimpar.setFocusPainted(false);
		add(btnLimpar);

		btnEntrar.addActionListener(this);
		btnLimpar.addActionListener(this);

		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEntrar) {
			validarLogin();
		}
		if (e.getSource() == btnLimpar) {
			txtUsuario.setText("");
			txtSenha.setText("");
			lblMensagem.setText("");
		}
	}

	private void validarLogin() {
		String loginDigitado = txtUsuario.getText();
		String senhaDigitada = new String(txtSenha.getPassword());

		if (usuarioValido.autenticar(loginDigitado, senhaDigitada)) {
			dispose();
			new TelaPrincipal();
		} else {
			lblMensagem.setText("Usuário ou senha incorretos!");
		}
	}
}