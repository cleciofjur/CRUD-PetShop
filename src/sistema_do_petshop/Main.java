package sistema_do_petshop;

public class Main {
    public static void main(String[] args) {
        Usuario admin = new Usuario("admin", "123", "Funcionário");
        new TelaLogin(admin);
    }
}