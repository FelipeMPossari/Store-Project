/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package possari.trabalholoja;


import java.io.File;

/**
 *
 * @author Felipe Possari
 * Classe que faz a chamada do método de telaUsuario
 * que abre o menu para o usuário além de abrir os arquivos
 * a serem lidos e chamar os métodos de leitura dos mesmos
 */
public class TrabalhoLoja {

    public static void main(String[] args) {
        DisplayLoja displayLoja = new DisplayLoja();
        File arquivoCategorias = new File ("C:/Users/Felipe/Downloads/categorias.txt") ;
        File arquivoProdutosFisicos = new File ("C:/Users/Felipe/Downloads/produtoFisico.txt") ;
        File arquivoProdutosVirtuais = new File ("C:/Users/Felipe/Downloads/produtoVirtual.txt") ;
        displayLoja.lerArquivoCategoria(arquivoCategorias);
        displayLoja.lerArquivoProdutoFisico(arquivoProdutosFisicos);
        displayLoja.lerArquivoProdutoVirtual(arquivoProdutosVirtuais);
        
        displayLoja.telaUsuario();
    }
}
