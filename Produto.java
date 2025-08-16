import java.io.Serializable;

public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private double preço, quantidade;
    private String nome, descrição, imagem;

    public Produto(int id, String nome,Double preço,Double quantidade, String descrição, String imagem) {
        this.id = id;
        this.preço = preço;
        this.quantidade = quantidade;
        this.nome = nome;
        this.descrição = descrição;
        this.imagem = imagem;
    }


    public int getId() {
        return id;
    }

    public double getPreço() {
        return preço;
    }

    public String getNome() {
        return nome;
    }

    public String getDescrição() {
        return descrição;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public void setQuantidade(Double quantidade){this.quantidade = quantidade;}


    public void adicionar(double quantidadeEsc) {
        if (quantidadeEsc <= 0) {
            System.out.println("A quantidade deve ser positiva!");
        }
        quantidade += quantidadeEsc;
    }


    public double multiplicar(double quantidadeEsc, double preco) {
        if (quantidadeEsc <= 0) {
            System.out.println("A quantidade deve ser positiva!");
        }

        if (quantidadeEsc > quantidade) {
            System.out.println("Quantidade de produto não disponível!");
        }

        double total = quantidadeEsc * preco;
        quantidade -= quantidadeEsc;
        return total;
    }

}
