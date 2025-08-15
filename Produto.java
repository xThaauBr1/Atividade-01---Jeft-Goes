import java.io.Serializable;

public class Produto implements Serializable {

    private static int ProximoId = 1;
    private int id;
    private double preço;
    private String nome, descrição, imagem;

    public Produto(String nome,Double preço, String descrição, String imagem) {
        this.id = ProximoId++;
        this.preço = preço;
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

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescrição(String descrição) {
        this.descrição = descrição;
    }

}
