package sistema_do_petshop;

import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaPrincipal extends JFrame implements ActionListener {
	JLabel lblNome, lblEspecie, lblRaca, lblDono, lblTelefone;
	JTextField txtNome, txtEspecie, txtRaca, txtDono, txtTelefone;
	
	JTextArea areaExibicao;
	
	JButton btnAdicionar, btnExcluir, btnConsultar, btnListar;
	JButton btnLimpar, btnAlterarCor, btnAlterarFonte, btnAgendamento;
	
	ArrayList<Animal> listaAnimais;
	
	public TelaPrincipal () {
		listaAnimais = new ArrayList<>();
		
		setTitle("Sistema Pet Shop - Cadastro de Animais");
        setSize(700, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
	}
}