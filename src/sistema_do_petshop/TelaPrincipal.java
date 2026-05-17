package sistema_do_petshop;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class TelaPrincipal extends JFrame implements ActionListener {

	JTextField txtAnimal, txtespecie, txtraca, txtnomedono,  txtele;
	ArrayList<Animal> listaAnimais = new ArrayList<>();
	JButton btadicionar;
	JTextArea areaexibicao;
	JButton btlistar;
	
    
    public TelaPrincipal() {
    	
    	

    	this.setTitle("Pet Shop - Tela Principal");
        this.setSize(800, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // BorderLayout divide a janela em zonas: NORTH, SOUTH, EAST, WEST, CENTER
        // É como um grid de 3x3 no CSS, mas bem mais simples
        this.setLayout(new BorderLayout());

        // ── Painel do topo ──────────────────────────────────────
        JPanel painelTopo = new JPanel();
        painelTopo.setBackground(new Color(15, 110, 86)); // verde escuro
        painelTopo.setPreferredSize(new Dimension(800, 50));

        JLabel lblTitulo = new JLabel("🐾 Pet Shop — Tela Principal");
        lblTitulo.setForeground(Color.WHITE);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 16));
        painelTopo.add(lblTitulo);

        // ── Painel esquerdo (formulário) ────────────────────────
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setLayout(
        	    new BoxLayout(painelEsquerdo, BoxLayout.Y_AXIS)
        	);
        painelEsquerdo.setBackground(new Color(245, 245, 245));
        painelEsquerdo.setPreferredSize(new Dimension(380, 500));

        
        // ── Painel direito (lista de animais) ───────────────────
        JPanel painelDireito = new JPanel();
        painelDireito.setBackground(Color.WHITE);

        // area de texto para exibir os animais cadastrados
        areaexibicao = new JTextArea();
        areaexibicao.setEditable(false); // o usuario nao pode digitar aq
        areaexibicao.setFont(new Font("Arial", Font.PLAIN, 13));
        areaexibicao.setBackground(new Color(245, 245, 245));
        
        
        // scrollpane add a barra de rolagem
        JScrollPane scroll = new JScrollPane(areaexibicao);
        scroll.setPreferredSize(new Dimension(380, 400));
        
        // botao listar
        btlistar = new JButton("Listar todos");
        btlistar.setPreferredSize(new Dimension(130, 35));
        btlistar.addActionListener(this);
        
        painelDireito.add(scroll);
        painelDireito.add(btlistar);
        
        

        // ── Adiciona os painéis na janela ───────────────────────
        // BorderLayout.NORTH = topo, CENTER = meio, etc.
        this.add(painelTopo, BorderLayout.NORTH);
        this.add(painelEsquerdo, BorderLayout.WEST);
        this.add(painelDireito, BorderLayout.CENTER);
        
        JLabel lblAnimal = new JLabel("Nome do animal");
        JLabel lblcadastro = new JLabel("CADASTRO DE ANIMAL");
        lblcadastro.setFont(new Font("Arial", Font.BOLD, 16));
        lblAnimal.setFont(new Font("Arial", Font.BOLD, 13)); // Font com F maiúsculo

        txtAnimal = new JTextField();
        txtAnimal.setPreferredSize(new Dimension(300, 30));
        txtAnimal.setMaximumSize(new Dimension(300,30));
        painelEsquerdo.add(lblcadastro);
        painelEsquerdo.add(lblAnimal); // adiciona no painel, não na janela
        painelEsquerdo.add(txtAnimal); // idem
        painelEsquerdo.add(Box.createVerticalStrut(8));
        
        JLabel lbxespecie = new JLabel("Espécie");
        txtespecie = new JTextField();
        txtespecie.setPreferredSize(new Dimension(300,30));
        txtespecie.setMaximumSize(new Dimension(300,30));
        painelEsquerdo.add(lbxespecie);
        painelEsquerdo.add(txtespecie);
        painelEsquerdo.add(Box.createVerticalStrut(8));
        
        JLabel lbxraca = new JLabel("Raça");
        txtraca = new JTextField();
        txtraca.setPreferredSize(new Dimension(300,30));
        txtraca.setMaximumSize(new Dimension(300,30));
        painelEsquerdo.add(lbxraca);
        painelEsquerdo.add(txtraca);
        painelEsquerdo.add(Box.createVerticalStrut(8));
        
        JLabel lbxnomedono = new JLabel("Nome do dono");
         txtnomedono = new JTextField();
        txtnomedono.setPreferredSize(new Dimension(300,30));
        txtnomedono.setMaximumSize(new Dimension(300,30));
        painelEsquerdo.add(lbxnomedono);
        painelEsquerdo.add(txtnomedono);
        painelEsquerdo.add(Box.createVerticalStrut(8));
        
        JLabel lbxtele = new JLabel("Telefone");
         txtele= new JTextField();
        txtele.setPreferredSize(new Dimension(300,30));
        txtele.setMaximumSize(new Dimension(300,30));
        painelEsquerdo.add(lbxtele);
        painelEsquerdo.add(txtele);
        painelEsquerdo.add(Box.createVerticalStrut(8));
        
        btadicionar = new JButton("Adicionar");
        btadicionar.setPreferredSize(new Dimension(130,35));
        btadicionar.addActionListener(this);
        painelEsquerdo.add(btadicionar);
        
        
        

        
        
        
        this.setVisible(true); // ← SEMPRE por último
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// ── Botão Adicionar ──────────────────────────────
	    if (e.getSource() == btadicionar) {

	        String nomeAnimal = txtAnimal.getText();
	        String especie    = txtespecie.getText();
	        String raca       = txtraca.getText();
	        String nomeDono   = txtnomedono.getText();
	        String telefone   = txtele.getText();

	        if (nomeAnimal.isEmpty() || especie.isEmpty() || raca.isEmpty()
	                || nomeDono.isEmpty() || telefone.isEmpty()) {
	            JOptionPane.showMessageDialog(this,
	                "Preencha todos os campos!",
	                "Atenção",
	                JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        Cliente dono   = new Cliente(nomeDono, telefone, "");
	        Animal  animal = new Animal(nomeAnimal, especie, raca, dono);
	        listaAnimais.add(animal);

	        JOptionPane.showMessageDialog(this,
	            "Animal cadastrado com sucesso!",
	            "Sucesso",
	            JOptionPane.INFORMATION_MESSAGE);
	        
	        txtAnimal.setText("");
	        txtespecie.setText("");
	        txtraca.setText("");
	        txtnomedono.setText("");
	        txtele.setText("");
	        
	    } // ← fecha o btadicionar aqui

	    // ── Botão Listar ─────────────────────────────────
	    if (e.getSource() == btlistar) { // ← irmão, não filho!

	        if (listaAnimais.isEmpty()) {
	            JOptionPane.showMessageDialog(this,
	                "Nenhum animal cadastrado!",
	                "Atenção",
	                JOptionPane.WARNING_MESSAGE);
	            return;
	        }

	        areaexibicao.setText("");

	        for (Animal a : listaAnimais) {
	            areaexibicao.append("Animal: "   + a.getNome()            + "\n");
	            areaexibicao.append("Espécie: "  + a.getEspecie()         + "\n");
	            areaexibicao.append("Raça: "     + a.getRaca()            + "\n");
	            areaexibicao.append("Dono: "     + a.getDono().getNome()  + "\n");
	            areaexibicao.append("Telefone: " + a.getDono().getTelefone() + "\n");
	            areaexibicao.append("─────────────────\n");
	        }

	    } 

	}
}