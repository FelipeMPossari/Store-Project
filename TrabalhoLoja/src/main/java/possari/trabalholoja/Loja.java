package possari.trabalholoja;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Possari
 * Classe de loja possui seus atributos e uma lista de
 * categorias. Possui vários métodos dentre eles o de adição e busca de
 * categorias e produtos.
 */
public class Loja {

    private String nome, cnpj, endereco;
    private ArrayList<Categoria> listaCategorias = new ArrayList<Categoria>();

    /**
     * Construtor de loja.
     */
    public Loja(String nome, String cnpj, String endereco) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.endereco = endereco;
    }

    /**
     * Método que adiciona categoria a lista de categorias.
     */
    public void adicionarCategoria(Categoria categoria) {
        listaCategorias.add(categoria);
    }

    /**
     * Método que adiciona um produto a lista de produtos
     */
    public void adicionarProduto(Produto produto, Categoria categoria) {
        categoria.adicionarProduto(produto);
    }

    /**
     * Método que busca produtos pelo número do id e retorna um objeto de
     * produto caso ele exista
     */
    public Produto buscarProduto(int id) {
        if (listaCategorias == null) {
            return null;
        }
        for (int i = 0; i < listaCategorias.size(); i++) {
            Categoria categoria = listaCategorias.get(i);
            List<Produto> listaDeProdutos = categoria.listarProdutos();
            for (int j = 0; j < listaDeProdutos.size(); j++) {
                Produto produto = listaDeProdutos.get(j);
                if (produto.getId() == id) {
                    return produto;
                }
            }
        }
        return null;
    }

    /**
     * Método que busca categoria pelo nome e retorna objeto de categoria caso
     * ela exista
     */
    public Categoria buscarCategoriaPorNome(String nome) {
        for (int i = 0; i < listaCategorias.size(); i++) {
            Categoria categoria = listaCategorias.get(i);
            if (categoria.getNome() == nome) {
                return categoria;
            }
        }
        return null;
    }

    /**
     * Método que busca categoria pelo código e retorna objeto de categoria caso
     * ela exista
     */
    public Categoria buscarCategoriaPorCodigo(int codigo) {
        if (listaCategorias == null) {
            return null;
        }
        for (int i = 0; i < listaCategorias.size(); i++) {
            Categoria categoria = listaCategorias.get(i);
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    /**
     * Método que remove produto da lista de produtos.
     */
    public void removerProduto(int id, Categoria categoria) {
        categoria.removerProduto(id);
    }

    /**
     * Método que retorna a lista de categorias
     */
    public ArrayList<Categoria> getCategorias() {
        return listaCategorias;
    }

}
