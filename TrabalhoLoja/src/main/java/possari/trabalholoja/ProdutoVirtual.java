package possari.trabalholoja;

/**
 *
 * @author Felipe Possari
 * Classe de produto virtual que herda atributos e métodos da classe produto 
 * Possui atributos e métodos próprios e utiliza o construtor de
 * produto
 */
public class ProdutoVirtual extends Produto {

    private double tamanhoArquivo;
    private String formato;
    /**
     * Construtor de produto virtual.
     */
    public ProdutoVirtual(int id, double preco, String nome, String descricao, String marca, Categoria categoria, int quantidade, double tamanhoArquivo, String formato) {
        super(id, preco, nome, descricao, marca, categoria, quantidade);
        this.tamanhoArquivo = tamanhoArquivo;
        this.formato = formato;
    }
    /**
     * Método que realiza o download do arquivo
     */
    public void realizarDownload() {
        System.out.println("Download realizado!");
    }
    /**
     * Métodos get e set de produto virtual
     */
    public String getFormato() {
        return formato;
    }

    public double getTamanhoArquivo() {
        return tamanhoArquivo;
    }
    
    public void setFormato(String novoFormato){
        this.formato = novoFormato;
    }
    
    public void setTamanhoArquivo(double novoTamanhoArquivo){
        this.tamanhoArquivo = novoTamanhoArquivo;
    }
}
