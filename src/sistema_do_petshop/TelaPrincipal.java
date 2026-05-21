package sistema_do_petshop;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class TelaPrincipal extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
        
        lblNome = new JLabel("Nome do animal: ");
        lblNome.setBounds(20, 20, 130, 25);
        add (lblNome);
        
        txtNome = new JTextField();
        txtNome.setBounds(155, 20, 180, 25);
        add (txtNome);
        
        lblEspecie = new JLabel("Espécie: ");
        lblEspecie.setBounds(20, 55, 130, 25);
        add (lblEspecie);
        
        txtEspecie = new JTextField();
        txtEspecie.setBounds(155, 55, 180, 25);
        add (txtEspecie);
        
        lblRaca = new JLabel("Raça: ");
        lblRaca.setBounds(20, 90, 130, 25);
        add (lblRaca);
        
        txtRaca = new JTextField();
        txtRaca.setBounds(155, 90, 180, 25);
        add(txtRaca);
        
        lblDono = new JLabel("Nome do Dono:");
        lblDono.setBounds(20, 125, 130, 25);
        add(lblDono);

        txtDono = new JTextField();
        txtDono.setBounds(155, 125, 180, 25);
        add(txtDono);

        lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 160, 130, 25);
        add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(155, 160, 180, 25);
        add(txtTelefone);

        btnAdicionar = new JButton("Adicionar");
        btnAdicionar.setBounds(20, 210, 120, 30);
        add(btnAdicionar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(150, 210, 120, 30);
        add(btnExcluir);

        btnConsultar = new JButton("Consultar");
        btnConsultar.setBounds(280, 210, 120, 30);
        add(btnConsultar);

        btnLimpar = new JButton("Limpar Campos");
        btnLimpar.setBounds(20, 250, 140, 30);
        add(btnLimpar);

        btnAlterarCor = new JButton("Alterar Cor");
        btnAlterarCor.setBounds(170, 250, 120, 30);
        add(btnAlterarCor);

        btnAlterarFonte = new JButton("Alterar Fonte");
        btnAlterarFonte.setBounds(300, 250, 130, 30);
        add(btnAlterarFonte);

        btnAgendamento = new JButton("Agendamento →");
        btnAgendamento.setBounds(440, 250, 150, 30);
        add(btnAgendamento);
        
        areaExibicao = new JTextArea();
        areaExibicao.setEditable(false);
        JScrollPane scroll = new JScrollPane(areaExibicao);
        scroll.setBounds(20, 295, 640, 200);
        add(scroll);
        
        btnAdicionar.addActionListener(this);
        btnExcluir.addActionListener(this);
        btnConsultar.addActionListener(this);
        btnLimpar.addActionListener(this);
        btnAlterarCor.addActionListener(this);
        btnAlterarFonte.addActionListener(this);
        btnAgendamento.addActionListener(this);
        
        setVisible(true);
	}
	
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
            new TelaAgendamento(listaAnimais); // passa a lista para o agendamento
        }
    }
	
	private void adicionarAnimal () {
		String nome = txtNome.getText().trim();
		String especie = txtEspecie.getText().trim();
		String raca = txtRaca.getText().trim();
		String nomeDono = txtDono.getText().trim();
		String tel = txtTelefone.getText().trim();
		
		if (nome.isEmpty() || nomeDono.isEmpty()) {
			areaExibicao.setText("Preencha com o nome do animal e do dono!");
			return;
		}
		
		Cliente dono = new Cliente (nomeDono, tel, "");
		Animal animal = new Animal (nome, especie, raca, dono);
		listaAnimais.add(animal);
		
		areaExibicao.setText("Animal adicionado com sucesso!\n Nome: " +nome+ " | Dono: " +nomeDono);
		limparCampos();
	}
	
	private void excluirAnimal () {
		String nome = txtNome.getText().trim();
		
		for (int i = 0; i < listaAnimais.size(); i++) {
			if (listaAnimais.get(i).getNome().equalsIgnoreCase(nome)) {
				listaAnimais.remove(i);
				areaExibicao.setText("Animal " +nome+ " excluído com sucesso!");
				limparCampos();
				return;
			}
		}
		areaExibicao.setText("Animal " +nome+ "não encotrado");
	}
	
	public void consultarAnimal () {
		String busca = txtNome.getText().trim();
		StringBuilder resultado = new StringBuilder();
	
		     for (Animal a : listaAnimais) {
		            if (a.getNome().equalsIgnoreCase(busca) ||
		                a.getDono().getNome().equalsIgnoreCase(busca)) {
		                resultado.append("Nome: ").append(a.getNome())
		                         .append(" | Espécie: ").append(a.getEspecie())
		                         .append(" | Raça: ").append(a.getRaca())
		                         .append(" | Dono: ").append(a.getDono().getNome())
		                         .append("\n");
		            }
		     }
		     
		     if (resultado.length() == 0) {
		            areaExibicao.setText("Nenhum animal encontrado.");
		        } else {
		            areaExibicao.setText(resultado.toString());
		        }
		    }
		
	private void limparCampos() {
        txtNome.setText("");
        txtEspecie.setText("");
        txtRaca.setText("");
        txtDono.setText("");
        txtTelefone.setText("");
    }
	
	 private void alterarCor() {
	        getContentPane().setBackground(new Color(173, 216, 230)); // azul claro
	        areaExibicao.setBackground(new Color(240, 248, 255));
	    }

	    private void alterarFonte() {
	        Font fonteNova = new Font("Arial", Font.BOLD, 14);
	        areaExibicao.setFont(fonteNova);
	        lblNome.setFont(fonteNova);
	        lblDono.setFont(fonteNova);
	    }
	}
