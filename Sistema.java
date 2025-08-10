import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.InvalidMarkException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;
import static javax.print.attribute.standard.MediaSizeName.C;

public class Sistema {

    Scanner scan = new Scanner(in);

    List<Produto> meuProduto = new ArrayList<>();

    public Sistema() {
        this.meuProduto = meuProduto;
    }


    public void CadastrarProdutos(){
        out.println("- Cadastro de produtos -\n");

        out.print("Digite o nome: ");
        String nome = scan.nextLine();

        out.print("Digite o preço: ");
        Double preco = scan.nextDouble();
        scan.nextLine();

        out.print("Digite a descrição: ");
        String descriçao = scan.nextLine();

        out.print("Cole o caminho da imagem: ");
        String imagem = scan.nextLine().replace("\"", "");

        Produto prod = new Produto(preco, nome, descriçao, imagem);
        meuProduto.add(prod);
        out.println("\nProduto " + prod.getId() + " adicionado com sucesso! ✅\n");

        String minhaPasta = "C:\\Users\\xThaau\\xThaau\\Atividade 01 - Jefté\\MeusProdutos";


        try {
            Path de = Path.of(imagem);

            if (!Files.exists(de)) {
                System.out.println("❌ Imagem não encontrada.");
                return;
            }

            BufferedImage testeImagem = ImageIO.read(de.toFile());
            if (testeImagem == null) {
                System.out.println("❌ O arquivo não é uma imagem válida.");
                return;
            }


            File pasta = new File(minhaPasta);
            String nomeImagem = String.valueOf(prod.getId()) + " - " + prod.getNome() + ".jpeg";
            Path para = Path.of(minhaPasta, nomeImagem);
            Files.copy(de, para);

            } catch (Exception e) {

            }
    }


    public void Editar(){

        out.println("- Editar Produtos -\n");

        out.print("Digite o id ou nome do produto: ");
        String idounome = scan.nextLine();

        boolean achou = false;

        for (Produto p : meuProduto){
            if(idounome.equalsIgnoreCase(p.getNome()) || idounome.equalsIgnoreCase(String.valueOf(p.getId()))){

                out.println("Produto "+p.getNome()+" encontrado! ✅\n");

                out.print("Digite o novo nome: ");
                String novoNome = scan.nextLine();
                p.setNome(novoNome);

                out.print("Digite o novo preço: ");
                Double novoPreço = scan.nextDouble();
                scan.nextLine();
                p.setPreço(novoPreço);

                out.print("Digite a nova descrição: ");
                String novaDescrição = scan.nextLine();
                p.setDescrição(novaDescrição);

                out.println("\nProduto alterado com sucesso! ✅");

                achou = true;
                break;
            }

        }
        if(!achou){
            out.println("\nProduto não encontrado! ❌");
        }
    }

    public void Excluir() {
        out.println("- Excluir Produtos -\n");

        out.print("Digite o id ou nome do produto: ");
        String idounome = scan.nextLine();

        boolean removido = meuProduto.removeIf(p ->
                idounome.equalsIgnoreCase(p.getNome()) ||
                        idounome.equalsIgnoreCase(String.valueOf(p.getId()))
        );

        if (removido) {
            out.println("\nProduto removido com sucesso! ✅\n");
        } else {
            out.println("\nProduto não encontrado! ❌");
        }
    }


    public void Listar(){
        out.println("- Listar Produtos -\n");
        for (Produto p : meuProduto){
            out.println("Id: " + p.getId());
            out.println("Nome: " + p.getNome());
            out.println("Preço: R$" + p.getPreço());
            out.println("Descrição: " + p.getDescrição());
            out.println("\n------------------------------------------->\n");
        }
}

    public void Vender() {
        out.println("- Produtos disponíveis -\n");

        for (Produto p : meuProduto) {
            out.println(p.getId() + " - " + p.getNome() + " = R$" + p.getPreço());
        }

        out.print("\nEscolha o produto pelo ID: ");
        int esc = scan.nextInt();
        scan.nextLine();

        Produto produtoSelecionado = null;

        for (Produto p : meuProduto) {
            if (p.getId() == esc) {
                produtoSelecionado = p;
                break;
            }
        }

        if (produtoSelecionado != null) {
            out.println("✅ Você comprou: " + produtoSelecionado.getNome() + " por R$" + produtoSelecionado.getPreço());
        } else {
            out.println("❌ Produto não encontrado.");
        }
    }



}
