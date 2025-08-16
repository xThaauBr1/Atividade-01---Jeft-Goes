import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.InvalidMarkException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;
import static javax.print.attribute.standard.MediaSizeName.C;

public class Sistema {

    Scanner scan = new Scanner(in);
    List<Produto> meusProdutos = new ArrayList<>();
    private GerenciadorTransferencia gerenciar;
    private int proximoId = 1;

    public Sistema() {
        this.gerenciar = new GerenciadorTransferencia();
        meusProdutos = gerenciar.carregandoProdutos();

        if(!meusProdutos.isEmpty()){
            proximoId = meusProdutos.stream().mapToInt(Produto::getId).max().orElse(0) + 1;
        }
    }

    private int getProximoId(){
        return proximoId++;
    }


    public void Cadastrar(){
        out.println("- Cadastro de produtos -\n");

        out.print("Digite o nome: ");
        String nome = scan.nextLine();

        out.print("Digite o preço: ");
        Double preco = scan.nextDouble();
        scan.nextLine();

        out.print("Digite a quantidade: ");
        Double quantidade = scan.nextDouble();
        scan.nextLine();

        out.print("Digite a descrição: ");
        String descriçao = scan.nextLine();

        out.print("Cole o caminho da imagem: ");
        String imagem = scan.nextLine().replace("\"", "");

        String minhaPasta = "C:\\Users\\xThaau\\Atividade 01 - Jefté\\MeusProdutos";


        if (!imagem.isBlank()) {
            try {
                Path de = Path.of(imagem);

                if (!Files.exists(de)) {
                    System.out.println("Imagem não encontrada!");
                    return;
                }

                BufferedImage testeImagem = ImageIO.read(de.toFile());
                if (testeImagem == null) {
                    System.out.println("Esse arquivo não é uma imagem válida!");
                    return;
                }

                File pasta = new File(minhaPasta);
                pasta.mkdirs();
                String nomeImagem = nome + ".jpeg";
                Path para = Path.of(minhaPasta, nomeImagem);
                Files.copy(de, para);


            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            imagem = null;
        }
        Produto prod = new Produto(getProximoId(),nome, preco, quantidade, descriçao, imagem);
        meusProdutos.add(prod);
        gerenciar.salvandoProdutos(meusProdutos);

        out.println("\nProduto " + prod.getId() + " adicionado com sucesso! ✅\n");
    }


    public void Editar(){

        out.println("- Editar Produtos -\n");

        out.print("Digite o id ou nome do produto: ");
        String idounome = scan.nextLine();

        boolean achou = false;

        for (Produto p : meusProdutos){
            if(idounome.equalsIgnoreCase(p.getNome()) || idounome.equalsIgnoreCase(String.valueOf(p.getId()))){

                out.println("Produto "+p.getNome()+" encontrado! ✅\n");

                out.print("Digite o novo nome: ");
                String novoNome = scan.nextLine();
                p.setNome(novoNome);

                out.print("Digite o novo preço: ");
                Double novoPreço = scan.nextDouble();
                scan.nextLine();
                p.setPreço(novoPreço);

                out.print("Adicione uma nova quantidade: ");
                Double novaQuantidade = scan.nextDouble();
                p.adicionar(novaQuantidade);

                out.print("Digite a nova descrição: ");
                String novaDescrição = scan.nextLine();
                scan.nextLine();
                p.setDescrição(novaDescrição);

                gerenciar.salvandoProdutos(meusProdutos);
                out.println("\nProduto alterado com sucesso! ✅");

                achou = true;
                break;
            }

        }
        if(!achou){
            out.println("\nProduto não encontrado!");
        }
    }

    public void Excluir() {
        out.println("- Excluir Produtos -\n");

        out.print("Digite o id ou nome do produto: ");
        String idounome = scan.nextLine();

        boolean removido = meusProdutos.removeIf(p ->
                idounome.equalsIgnoreCase(p.getNome()) ||
                        idounome.equalsIgnoreCase(String.valueOf(p.getId()))
        );

        if (removido) {
            gerenciar.salvandoProdutos(meusProdutos);
            out.println("\nProduto removido com sucesso! ✅\n");
        } else {
            out.println("\nProduto não encontrado!");
        }
    }

    private void listarProdutos(Produto p) {
        out.println("Id: " + p.getId());
        out.println("Nome: " + p.getNome());
        out.println("Preço: R$ " + p.getPreço());
        out.println("Quantidade: " + p.getQuantidade());
        out.println("Descrição: " + p.getDescrição());
        out.println("\n------------------------------\n");
    }

    public void Listar(){
        out.println("- Listar Produtos -\n");

        if(meusProdutos.isEmpty()){
            out.println("Nenhum produto cadastrado!\n");
        }

        for (Produto p : meusProdutos){
            listarProdutos(p);
        }
}

    public void Vender() {
        out.println("- Vender -\n");

        if (meusProdutos.isEmpty()) {
            out.println("Nenhum produto disponível");
            return;
        }

        for (Produto p : meusProdutos) {
            listarProdutos(p);
        }

        out.print("Qual produto você quer? (Digite o id): ");
        if (!scan.hasNextInt()) {
            out.println("ID inválido!");
            scan.nextLine();
            return;
        }
        int idprod = scan.nextInt();

        Produto produtoEsc = null;
        for (Produto p : meusProdutos) {
            if (idprod == p.getId()) {
                produtoEsc = p;
                break;
            }
        }

        if (produtoEsc == null) {
            out.println("Produto não encontrado");
            return;
        }

        out.print("\nDigite a quantidade do " + produtoEsc.getNome() +
                " (" + produtoEsc.getQuantidade() + "): ");
        if (!scan.hasNextDouble()) {
            out.println("Quantidade inválida!");
            scan.nextLine();
            return;
        }
        double vendaQuantidade = scan.nextDouble();

        if (vendaQuantidade > produtoEsc.getQuantidade()) {
            out.println("Quantidade de produto não disponível!");
            return;
        }

        double total = produtoEsc.multiplicar(vendaQuantidade, produtoEsc.getPreço());
        out.println("Pedido feito com sucesso! ✅");
        out.println("\n- Resumo da venda -\n");
        out.println("Nome: " + produtoEsc.getNome());
        out.println("Quantidade: " + vendaQuantidade);
        out.println("Preço total: " + total + " R$");
        gerenciar.salvandoProdutos(meusProdutos);
    }

}

