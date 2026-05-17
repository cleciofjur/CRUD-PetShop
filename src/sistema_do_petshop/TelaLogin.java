package sistema_do_petshop;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 * Tela de Login — primeira tela exibida ao usuário.
 * Responsabilidade: apenas capturar os dados e repassar ao Controller.
 */
public class TelaLogin extends JFrame {

    // ──────────────── Componentes ────────────────
    private JTextField      campoNome;
    private JPasswordField  campoSenha;
    private JButton         botaoEntrar;
    private JLabel          labelMensagem;

    // Controller (a tela não conhece a lógica — só delega)
    private LoginController controller;

    // ──────────────── Paleta ────────────────
    private static final Color COR_FUNDO       = new Color(15, 23, 42);
    private static final Color COR_PAINEL      = new Color(30, 41, 59);
    private static final Color COR_DESTAQUE    = new Color(99, 102, 241);
    private static final Color COR_TEXTO       = new Color(248, 250, 252);
    private static final Color COR_TEXTO_SUAVE = new Color(148, 163, 184);
    private static final Color COR_ERRO        = new Color(239, 68, 68);
    private static final Color COR_BORDA       = new Color(51, 65, 85);

    public TelaLogin() {
        this.controller = new LoginController(this);
        configurarJanela();
        adicionarComponentes();
        setVisible(true);
    }

    private void configurarJanela() {
        setTitle("Login — Sistema de Agendamento");
        setSize(420, 520);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        getContentPane().setBackground(COR_FUNDO);
        setLayout(new GridBagLayout()); // centraliza o card
    }

    private void adicionarComponentes() {
        JPanel card = criarCard();
        add(card);
    }

    // ──────────────── Card central ────────────────
    private JPanel criarCard() {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(COR_PAINEL);
        card.setBorder(new CompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(40, 40, 36, 40)
        ));
        card.setPreferredSize(new Dimension(360, 420));

        card.add(criarCabecalho());
        card.add(Box.createVerticalStrut(28));
        card.add(criarFormulario());
        card.add(Box.createVerticalStrut(20));
        card.add(criarBotaoEntrar());
        card.add(Box.createVerticalStrut(12));
        card.add(criarLabelMensagem());

        return card;
    }

    // ──────────────── Cabeçalho ────────────────
    private JPanel criarCabecalho() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);

        JLabel icone = new JLabel("🔐");
        icone.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        icone.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel titulo = new JLabel("Bem-vindo de volta");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(COR_TEXTO);
        titulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitulo = new JLabel("Faça login para continuar");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitulo.setForeground(COR_TEXTO_SUAVE);
        subtitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        painel.add(icone);
        painel.add(Box.createVerticalStrut(8));
        painel.add(titulo);
        painel.add(Box.createVerticalStrut(4));
        painel.add(subtitulo);

        return painel;
    }

    // ──────────────── Formulário ────────────────
    private JPanel criarFormulario() {
        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));
        painel.setOpaque(false);

        // Nome
        painel.add(criarRotulo("Usuário"));
        painel.add(Box.createVerticalStrut(6));
        campoNome = criarCampoTexto("Digite seu nome de usuário");
        painel.add(campoNome);

        painel.add(Box.createVerticalStrut(16));

        // Senha
        painel.add(criarRotulo("Senha"));
        painel.add(Box.createVerticalStrut(6));
        campoSenha = criarCampoSenha("••••••••");
        painel.add(campoSenha);

        // Enter também envia
        ActionListener acaoLogin = e -> controller.tentarLogin(
            campoNome.getText(),
            new String(campoSenha.getPassword())
        );
        campoNome.addActionListener(acaoLogin);
        campoSenha.addActionListener(acaoLogin);

        return painel;
    }

    // ──────────────── Botão ────────────────
    private JButton criarBotaoEntrar() {
        botaoEntrar = new JButton("Entrar →");
        botaoEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        botaoEntrar.setForeground(Color.WHITE);
        botaoEntrar.setBackground(COR_DESTAQUE);
        botaoEntrar.setFocusPainted(false);
        botaoEntrar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoEntrar.setMaximumSize(new Dimension(Integer.MAX_VALUE, 44));
        botaoEntrar.setBorder(new CompoundBorder(
            new LineBorder(COR_DESTAQUE, 1, true),
            new EmptyBorder(10, 0, 10, 0)
        ));
        botaoEntrar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        botaoEntrar.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) { botaoEntrar.setBackground(new Color(79, 70, 229)); }
            public void mouseExited(MouseEvent e)  { botaoEntrar.setBackground(COR_DESTAQUE); }
        });

        botaoEntrar.addActionListener(e -> controller.tentarLogin(
            campoNome.getText(),
            new String(campoSenha.getPassword())
        ));

        return botaoEntrar;
    }

    // ──────────────── Label de feedback ────────────────
    private JLabel criarLabelMensagem() {
        labelMensagem = new JLabel(" ");
        labelMensagem.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        labelMensagem.setForeground(COR_ERRO);
        labelMensagem.setAlignmentX(Component.CENTER_ALIGNMENT);
        return labelMensagem;
    }

    // ──────────────── Métodos públicos chamados pelo Controller ────────────────

    /** Exibe uma mensagem de erro na tela. */
    public void mostrarErro(String mensagem) {
        labelMensagem.setForeground(COR_ERRO);
        labelMensagem.setText("⚠ " + mensagem);
        campoSenha.setText("");
        campoSenha.requestFocus();
    }

    /** Fecha esta janela e abre a tela de agendamento. */
    public void irParaAgendamento(String nomeUsuario) {
        dispose();
        new TelaAgendamento(nomeUsuario);
    }

    // ──────────────── Helpers de UI ────────────────
    private JLabel criarRotulo(String texto) {
        JLabel label = new JLabel(texto);
        label.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        label.setForeground(COR_TEXTO_SUAVE);
        label.setAlignmentX(Component.LEFT_ALIGNMENT);
        return label;
    }

    private JTextField criarCampoTexto(String placeholder) {
        JTextField campo = new JTextField() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty() && !isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(COR_TEXTO_SUAVE);
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    Insets ins = getInsets();
                    g2.drawString(placeholder, ins.left + 2, getHeight() - ins.bottom - 5);
                    g2.dispose();
                }
            }
        };
        estilizarCampo(campo);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return campo;
    }

    private JPasswordField criarCampoSenha(String placeholder) {
        JPasswordField campo = new JPasswordField() {
            @Override protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getPassword().length == 0 && !isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(COR_TEXTO_SUAVE);
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    Insets ins = getInsets();
                    g2.drawString(placeholder, ins.left + 2, getHeight() - ins.bottom - 5);
                    g2.dispose();
                }
            }
        };
        estilizarCampo(campo);
        campo.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        return campo;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        campo.setBackground(COR_FUNDO);
        campo.setForeground(COR_TEXTO);
        campo.setCaretColor(COR_TEXTO);
        campo.setAlignmentX(Component.LEFT_ALIGNMENT);
        campo.setBorder(new CompoundBorder(
            new LineBorder(COR_BORDA, 1, true),
            new EmptyBorder(10, 12, 10, 12)
        ));

        // Borda iluminada no foco
        campo.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                campo.setBorder(new CompoundBorder(
                    new LineBorder(COR_DESTAQUE, 1, true),
                    new EmptyBorder(10, 12, 10, 12)
                ));
            }
            public void focusLost(FocusEvent e) {
                campo.setBorder(new CompoundBorder(
                    new LineBorder(COR_BORDA, 1, true),
                    new EmptyBorder(10, 12, 10, 12)
                ));
            }
        });
    }
}
