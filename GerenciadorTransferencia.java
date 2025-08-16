import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorTransferencia {

    private final String Arquivo = "Produtos_cadastrados.dat";

    public GerenciadorTransferencia() {
    }

    public void salvandoProdutos(List<Produto> lista){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(Arquivo))){
            out.writeObject(lista);
        }catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    @SuppressWarnings("unchecked")
    public List<Produto> carregandoProdutos(){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(Arquivo))){
            return (List<Produto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }


}
