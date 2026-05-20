package sistema_do_petshop;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TelaLogin extends JFrame implements ActionListener {
	JLabel lblUsuario, lblSenha, lblMensagem;
	JTextField txtUsuario;
	JPasswordField txtSenha;
	JButton btnEntrar, btnLimpar;
	
	Usuario usuarioValido;
	
	public TelaLogin(Usuario usuarioValido) {
		this.usuarioValido = usuarioValido;
		
		setTitle("Login - PetSHop");
		setSize(350, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setLayout(null);
		
		lblUsuario = new JLabel("Usuário: ");
		lblUsuario.setBounds(30, 30, 80, 25);
		add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(110, 30, 80, 25);
		add(txtUsuario);
		
		lblSenha = new JLabel("Senha: ");
		lblSenha.setBounds(30, 30, 80, 25);
		add(lblSenha);
		
		txtSenha = new JPasswordField();
		txtSenha.setBounds(110, 30, 80, 25);
		add(txtSenha);
		
		lblMensagem = new JLabel("");
		lblMensagem.setBounds(30, 110, 280, 25);
		lblMensagem.setForeground(Color.RED);
		add (lblMensagem);
		
		btnEntrar = new JButton("Entrar");
		btnEntrar.setBounds(60, 150, 100, 30);
		add(btnEntrar);
		
		btnLimpar = new JButton("Limpar");
		btnLimpar.setBounds(180, 150, 100, 30);
		add(btnLimpar);
		
		btnEntrar.addActionListener(this);
		btnLimpar.addActionListener(this);
		
		setVisible(true);
	}

	public void actionPerformed (ActionEvent e) {
		if (e.getSource() == btnEntrar) {
			validarLogin();
		}
		
		if (e.getSource() == btnLimpar) {
			txtUsuario.setText("");
			txtSenha.setText("");
			lblMensagem.setText("");
		}
	}
	
	private void validarLogin () {
		String loginDigitado = txtUsuario.getText();
		String senhaDigitada = new String (txtSenha.getPassword());
		
		if (usuarioValido.autenticar(loginDigitado, senhaDigitada)) {
			dispose();
			new TelaPrincipal();
		} else {
			lblMensagem.setText("Usuário ou senha incorretas!");
		}
	}
}
