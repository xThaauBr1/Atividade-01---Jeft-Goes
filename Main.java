import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Sistema meuSistema = new Sistema();
        int esc;

        do {
            System.out.print("1 - Cadastrar produto\n" +
                    "2 - Editar\n" +
                    "3 - Excluir\n" +
                    "4 - Listar\n" +
                    "5 - Vender\n" +
                    "0 - Sair\n> ");

            esc = scan.nextInt();
            switch (esc){
                case 1 -> meuSistema.CadastrarProdutos();
                case 2 -> meuSistema.Editar();
                case 3 -> meuSistema.Excluir();
                case 4 -> meuSistema.Listar();
                case 5 -> meuSistema.Vender();
                case 0 -> {
                    System.out.println("Saindo do sistema...");
                }

            }

        }while (esc != 0);
    }
}
