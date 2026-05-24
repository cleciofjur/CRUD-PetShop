package sistema_do_petshop;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;

public class TelaAbertura implements ActionListener {

    JFrame tela = new JFrame("Pet Shop Paraíso dos Pets");
    JPanel painel = new JPanel();
    JButton btsalvar = new JButton("Entrar");
    JButton btexibir = new JButton("Sair");
    JLabel lbtitulo = new JLabel("Pet Shop Paraíso dos Pets");
    JLabel lbsubtitulo = new JLabel("Porque seu pet merece o melhor!");
    JLabel lblImagem;
    JLabel lblCachorro;
    JLabel lblPata1;
    JLabel lblPata2;
    JLabel lblPata3;
    JLabel lblBanho = new JLabel("Banho");
    JLabel lblTosa = new JLabel("Tosa");
    JLabel lblConsulta = new JLabel("Consulta");

    void criarTela() {
        tela.setSize(480, 420);
        tela.setLocation(500, 250);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        painel.setLayout(null);
        painel.setBackground(new Color(100, 180, 255));

        // gato
        ImageIcon icone = new ImageIcon("C:/Users/eluan/Downloads/CRUD-PetShop-main/CRUD-PetShop-main/src/Imagens/cat_PNG50527.png");
        Image img = icone.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        lblImagem = new JLabel(new ImageIcon(img));
        lblImagem.setBounds(10, 20, 90, 90);
        painel.add(lblImagem);

        // cachorro canto inferior direito
        ImageIcon iconeCachorro = new ImageIcon("C:/Users/eluan/Downloads/CRUD-PetShop-main/CRUD-PetShop-main/src/Imagens/59-dog-png-image.png");
        Image imgCachorro = iconeCachorro.getImage().getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        lblCachorro = new JLabel(new ImageIcon(imgCachorro));
        lblCachorro.setBounds(350, 270, 110, 110);
        painel.add(lblCachorro);

        // patinha 1 - Banho
        ImageIcon icone2 = new ImageIcon("C:/Users/eluan/Downloads/CRUD-PetShop-main/CRUD-PetShop-main/src/Imagens/paw_PNG27.png");
        Image img2 = icone2.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lblPata1 = new JLabel(new ImageIcon(img2));
        lblPata1.setBounds(160, 130, 30, 30);
        painel.add(lblPata1);

        // patinha 2 - Tosa
        ImageIcon icone3 = new ImageIcon("C:/Users/eluan/Downloads/CRUD-PetShop-main/CRUD-PetShop-main/src/Imagens/paw_PNG27.png");
        Image img3 = icone3.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lblPata2 = new JLabel(new ImageIcon(img3));
        lblPata2.setBounds(160, 175, 30, 30);
        painel.add(lblPata2);

        // patinha 3 - Consulta
        ImageIcon icone4 = new ImageIcon("C:/Users/eluan/Downloads/CRUD-PetShop-main/CRUD-PetShop-main/src/Imagens/paw_PNG27.png");
        Image img4 = icone4.getImage().getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        lblPata3 = new JLabel(new ImageIcon(img4));
        lblPata3.setBounds(160, 220, 30, 30);
        painel.add(lblPata3);

        // título
        lbtitulo.setBounds(95, 55, 300, 40);
        lbtitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lbtitulo.setForeground(Color.WHITE);

        // subtítulo
        lbsubtitulo.setBounds(130, 95, 280, 25);
        lbsubtitulo.setFont(new Font("Arial", Font.PLAIN, 13));
        lbsubtitulo.setForeground(Color.WHITE);

        // labels de serviço
        lblBanho.setBounds(195, 133, 100, 25);
        lblBanho.setFont(new Font("Arial", Font.BOLD, 14));
        lblBanho.setForeground(Color.WHITE);

        lblTosa.setBounds(195, 178, 100, 25);
        lblTosa.setFont(new Font("Arial", Font.BOLD, 14));
        lblTosa.setForeground(Color.WHITE);

        lblConsulta.setBounds(195, 223, 100, 25);
        lblConsulta.setFont(new Font("Arial", Font.BOLD, 14));
        lblConsulta.setForeground(Color.WHITE);

        // botão entrar - azul escuro
        btsalvar.setBounds(160, 300, 120, 30);
        btsalvar.setBackground(new Color(30, 100, 180));
        btsalvar.setForeground(Color.WHITE);
        btsalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btsalvar.setFocusPainted(false);
        btsalvar.addActionListener(this);

        // botão sair - branco
        btexibir.setBounds(160, 340, 120, 30);
        btexibir.setBackground(Color.WHITE);
        btexibir.setForeground(new Color(100, 180, 255));
        btexibir.setFont(new Font("Arial", Font.BOLD, 14));
        btexibir.setFocusPainted(false);
        btexibir.addActionListener(this);

        painel.add(lbtitulo);
        painel.add(lbsubtitulo);
        painel.add(lblBanho);
        painel.add(lblTosa);
        painel.add(lblConsulta);
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
            Usuario usuario = new Usuario("admin", "123", "admin");
            TelaLogin telaLogin = new TelaLogin(usuario);
            telaLogin.setVisible(true);
            tela.setVisible(false);
        }

        if(e.getSource() == btexibir) {
            System.exit(0);
        }
    }
}