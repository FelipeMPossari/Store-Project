/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possari.trabalholoja;

import java.util.ArrayList;

/**
 *
 * @author Felipe Possari
 * Classe de categoria que possui seus atributos e uma lista de
 * produtos Possui vários métodos get e set além de busca, adição e remoção
 */
public class Categoria {

    private int codigo;
    private String nome, descricao;
    private ArrayList<Produto> listaProdutos = new ArrayList<>();

    /**
     * Construtor de categoria.
     */
    public Categoria(String nome, String descricao, int codigo) {
        this.nome = nome;
        this.descricao = descricao;
        this.codigo = codigo;
    }

    /**
     * Método que adiciona produto a lista de produtos
     */
    public void adicionarProduto(Produto produto) {
        listaProdutos.add(produto);
    }

    /**
     * Método que remove produto da lista de produtos
     */
    public void removerProduto(int id) {
        Produto produto;
        if (buscarProdutoPorId(id) != null) {
            produto = buscarProdutoPorId(id);
            listaProdutos.remove(produto);
            System.out.println("Produto removido!");
        } else {
            System.out.println("Produto não encontrado!");
        }
    }

    /**
     * Método que busca produtos pelo número do ID e retorna um objeto de
     * produto caso exista
     */
    public Produto buscarProdutoPorId(int id) {
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            if (produto.getId() == id) {
                return produto;
            }
        }
        return null;
    }

    /**
     * Método que busca produtos pelo nome e retorna um objeto de produto caso
     * exista
     */
    public Produto buscarProdutoPorNome(String nome) {
        for (int i = 0; i < listaProdutos.size(); i++) {
            Produto produto = listaProdutos.get(i);
            if (produto.getNome() == nome) {
                return produto;
            }
        }
        return null;
    }

    /**
     * Método que retorna a lista de produtos
     */
    public ArrayList<Produto> listarProdutos() {
        return listaProdutos;
    }

    /**
     * Métodos get e set dos atributos de categoria
     */
    public String getNome() {
        return nome;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setNome(String novoNome) {
        this.nome = novoNome;
    }

    public void setDescricao(String novaDescricao) {
        this.descricao = novaDescricao;
    }

    public void setCodigo(int novoCodigo) {
        this.codigo = novoCodigo;
    }

}
