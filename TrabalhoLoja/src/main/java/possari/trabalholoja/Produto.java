package possari.trabalholoja;

/**
 * @author Felipe Possari
 * Classe abstrata de produto que possui métodos concretos e atributos que são
 * herdados pelas classes ProdutoFisico e ProdutoVirtual.
 */
public abstract class Produto {

    private int id, quantidade;
    private double preco;
    private String nome, descricao, marca;
    private Categoria categoria;

    /**
     * Construtor de produto
     */
    public Produto(int id, double preco, String nome, String descricao, String marca, Categoria categoria, int quantidade) {
        this.id = id;
        this.preco = preco;
        this.nome = nome;
        this.descricao = descricao;
        this.marca = marca;
        this.categoria = categoria;
        this.quantidade = quantidade;
    }

    /**
     * Método que diminui o estoque do produto que foi vendido
     */
    public void venderProduto(Produto produto) {
        int quantidade = produto.getQuantidade();
        produto.setQuantidade(quantidade - 1);

    }

    /**
     * Métodos get e set dos atributos de produto
     */
    public int getId() {
        return id;
    }

    public double getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getMarca() {
        return marca;
    }

    public String getNomeCategoria() {
        String nomeCategoria = categoria.getNome();
        return nomeCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int novaQuantidade) {
        this.quantidade = novaQuantidade;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setCategoria(Categoria novaCategoria) {
        this.categoria = novaCategoria;
    }

    public void setMarca(String novaMarca) {
        this.marca = novaMarca;
    }

    public void setDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }

    public void setPreco(double novoPreco) {
        this.preco = novoPreco;
    }

    public void setId(int novoId) {
        this.id = novoId;
    }

}
