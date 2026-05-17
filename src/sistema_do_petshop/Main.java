package sistema_do_petshop;

import javax.swing.SwingUtilities;

/**
 * Ponto de entrada da aplicação.
 * SwingUtilities.invokeLater garante que a interface gráfica
 * seja criada na thread correta (Event Dispatch Thread).
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(TelaLogin::new);
    }
}
