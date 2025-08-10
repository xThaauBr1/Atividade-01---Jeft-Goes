public class Produto {

    private static int ProximoId = 1;
    private int id;
    private double preço;
    private String nome, descrição, imagem;

    public Produto(double preço, String nome, String descrição, String imagem) {
        this.id = ProximoId++;
        this.preço = preço;
        this.nome = nome;
        this.descrição = descrição;
        this.imagem = imagem;
    }

    public Produto(double preço, String nome, String descrição) {
        this.id = ProximoId++;
        this.preço = preço;
        this.nome = nome;
        this.descrição = descrição;
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

    public String getImagem() {
        return imagem;
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

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }
}
