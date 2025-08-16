import java.io.Serializable;

public class Produto implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private double preço, quantidade;
    private String nome, descrição, imagem;

    public Produto(int id, String nome,Double preço, String descrição, String imagem) {
        this.id = id;
        this.preço = preço;
        this.quantidade = quantidade;
        this.nome = nome;
        this.descrição = descrição;
        this.imagem = imagem;
    }

    public double getQuantidade() {
        return quantidade;
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

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

    public double multiplicar(){
        return quantidade * preço;
    }

}
