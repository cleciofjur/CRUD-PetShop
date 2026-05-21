package sistema_do_petshop;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaAbertura implements ActionListener {

    JFrame tela = new JFrame("Pet Shop Paraiso dos Pets");
    JPanel painel = new JPanel();
    JButton btsalvar = new JButton("Entrar");
    JButton btexibir = new JButton("Sair");
    JLabel lbtitulo = new JLabel("🐾 Pet Shop Paraiso dos Pets");
    JLabel lbsubtitulo = new JLabel("Porque seu pet merece o melhor!");

    void criarTela() {
        tela.setSize(400, 300);
        tela.setLocation(500, 250);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painel.setLayout(null);
        painel.setBackground(new Color(100, 180, 255));

        lbtitulo.setBounds(50, 40, 300, 35);
        lbtitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lbtitulo.setForeground(Color.WHITE);

        lbsubtitulo.setBounds(50, 75, 300, 25);
        lbsubtitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        lbsubtitulo.setForeground(Color.WHITE);

        btsalvar.setBounds(130, 130, 120, 40);
        btsalvar.setBackground(Color.WHITE);
        btsalvar.setForeground(new Color(100, 180, 255));
        btsalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btsalvar.setFocusPainted(false);
        btsalvar.addActionListener(this);

        btexibir.setBounds(130, 180, 120, 40);
        btexibir.setBackground(new Color(100, 180, 255));
        btexibir.setForeground(Color.WHITE);
        btexibir.setFont(new Font("Arial", Font.PLAIN, 14));
        btexibir.setFocusPainted(false);
        btexibir.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btexibir.addActionListener(this);

        painel.add(lbtitulo);
        painel.add(lbsubtitulo);
        painel.add(btsalvar);
        painel.add(btexibir);

        tela.getContentPane().add(painel);
        tela.setVisible(true);
    }

    public static void main(String[] args) {
        TelaAbertura tela = new TelaAbertura();
        tela.criarTela();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == btsalvar) {
            JOptionPane.showMessageDialog(null, "Bem vindo ao Pet Shop Paraiso dos Pets!");
        }

        if(e.getSource() == btexibir) {
            System.exit(0);
        }
    }
}