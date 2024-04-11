/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package possari.trabalholoja;

/**
 *
 * @author Felipe Possari
 * Felipe Classe de produto físico que herda atributos e métodos da
 * classe produto Possui atributos e métodos próprios e utiliza o construtor de
 * produto
 */
public class ProdutoFisico extends Produto {

    private double peso;
    private String dimensoes;

    /**
     * Construtor de produto físico
     */
    public ProdutoFisico(int id, double preco, String nome, String descricao, String marca, Categoria categoria, int quantidade, double peso, String dimensoes) {
        super(id, preco, nome, descricao, marca, categoria, quantidade);
        this.peso = peso;
        this.dimensoes = dimensoes;
    }

    /**
     * Método que calcula o frete do produto físico
     */
    public double calcularFrete() {
        if (peso < 5) {
            return peso * 1.3;
        }
        if (peso > 5 && peso < 10) {
            return peso * 1.5;
        }
        if (peso > 10 && peso < 20) {
            return peso * 1.75;
        } else {
            return peso * 2;
        }
    }

    /**
     * Métodos get e set dos atributos de produto físico
     */
    public double getPeso() {
        return peso;
    }

    public String getDimensoes() {
        return dimensoes;
    }

    public void setPeso(double novoPeso) {
        this.peso = novoPeso;
    }

    public void setDimensoes(String novasDimensoes) {
        this.dimensoes = novasDimensoes;
    }
}
