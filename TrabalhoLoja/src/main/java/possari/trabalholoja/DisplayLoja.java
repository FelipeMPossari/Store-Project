package possari.trabalholoja;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Felipe Possari 
 * Classe DisplayLoja realiza as chamadas de métodos de
 * todas as outras classes do projeto além de exibir todos os menus de opção e
 * mensagens de erro ou sucesso. Possui uma lista de produtos que estão no
 * carrinho e uma instancia da classe loja.
 *
 */
public class DisplayLoja {

    private ArrayList<Produto> produtosNoCarrinho = new ArrayList<Produto>();
    private Loja loja = new Loja("Tem de tudo", "58.588.061/0001-00", "Rua das rosas");

    /**
     * Método para ler os campos do arquivo de categoria ignorando a primeira
     * linha do arquivo pois não são dados úteis
     */
    public void lerArquivoCategoria(File arquivo) {
        Scanner scanner = null;
        String nome, descricao;
        int codigo, contador = 0;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {

            Logger.getLogger(TrabalhoLoja.class.getName()).log(Level.SEVERE, null, ex);

        }
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (contador == 0) {
                linha = scanner.nextLine();
            }

            String[] campos = linha.split("#");

            codigo = Integer.valueOf(campos[0]);
            nome = campos[1];
            descricao = campos[2];
            Categoria categoria = new Categoria(nome, descricao, codigo);
            loja.adicionarCategoria(categoria);
            contador = 1;

        }
    }

    /**
     * Método para ler os campos do arquivo de produto físico ignorando a
     * primeira linha do arquivo pois não são dados úteis
     */
    public void lerArquivoProdutoFisico(File arquivo) {
        Scanner scanner = null;
        Categoria categoria;
        String nome, descricao, marca, dimensoes;
        int id, contador = 0, codigoCategoria, quantidade;
        double preco, peso;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {

            Logger.getLogger(TrabalhoLoja.class.getName()).log(Level.SEVERE, null, ex);

        }
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (contador == 0) {
                linha = scanner.nextLine();
            }

            String[] campos = linha.split("#");

            id = Integer.valueOf(campos[0]);
            nome = campos[1];
            preco = Double.valueOf(campos[2]);
            descricao = campos[3];
            marca = campos[4];
            codigoCategoria = Integer.valueOf(campos[5]);
            peso = Double.valueOf(campos[6]);
            dimensoes = campos[7];
            quantidade = 2;
            categoria = loja.buscarCategoriaPorCodigo(codigoCategoria);
            ProdutoFisico produtoFisico = new ProdutoFisico(id, preco, nome, descricao, marca, categoria, quantidade, peso, dimensoes);
            loja.adicionarProduto(produtoFisico, categoria);
            contador = 1;

        }
    }

    /**
     * Método para ler os campos do arquivo de produto virtual ignorando a
     * primeira linha do arquivo pois não são dados úteis
     */
    public void lerArquivoProdutoVirtual(File arquivo) {
        Scanner scanner = null;
        Categoria categoria;
        String nome, descricao, marca, formato, stringTamanho;
        int id, contador = 0, codigoCategoria, quantidade;
        double preco, tamanho;
        try {
            scanner = new Scanner(arquivo);
        } catch (FileNotFoundException ex) {

            Logger.getLogger(TrabalhoLoja.class.getName()).log(Level.SEVERE, null, ex);

        }
        while (scanner.hasNextLine()) {
            String linha = scanner.nextLine();
            if (contador == 0) {
                linha = scanner.nextLine();
            }

            String[] campos = linha.split("#");

            id = Integer.valueOf(campos[0]);
            nome = campos[1];
            preco = Double.valueOf(campos[2]);
            descricao = campos[3];
            marca = campos[4];
            codigoCategoria = Integer.valueOf(campos[5]);
            stringTamanho = campos[6].substring(0, 4);
            tamanho = Double.valueOf(stringTamanho);
            formato = campos[7];
            quantidade = 2;
            categoria = loja.buscarCategoriaPorCodigo(codigoCategoria);
            ProdutoVirtual produtoVirtual = new ProdutoVirtual(id, preco, nome, descricao, marca, categoria, quantidade, tamanho, formato);
            loja.adicionarProduto(produtoVirtual, categoria);
            contador = 1;

        }
    }

    /**
     * Método para mostrar o menu de opções para o usuário do sistema
     */
    public void telaUsuario() {
        Scanner sc = new Scanner(System.in);
        int opcao = 1;
        while (opcao != 0) {
            System.out.println("Digite a opção que deseja realizar\n 1 - Buscar produtos\n" + " 2 - Buscar categorias\n"
                    + " 3 - Gerenciar categorias\n 4 - Gerenciar produtos\n 5 - Adicionar ao carrinho\n "
                    + "6 - Realizar compra\n 7 - Listar carrinho\n 0 - Finalizar");
            opcao = sc.nextInt();
            sc.nextLine();
            switch (opcao) {
                case 1:
                    buscarProdutos();
                    break;
                case 2:
                    buscarCategorias();
                    break;
                case 3:
                    gerenciarCategorias();
                    break;
                case 4:
                    gerenciarProdutos();
                    break;
                case 5:
                    adicionarAoCarrinho();
                    break;
                case 6:
                    realizarCompra();
                    break;
                case 7:
                    listarCarrinho();
                    break;
                case 0:
                    System.out.println("Encerrando sistema.");
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        }
    }

    /**
     * Método para listar os produtos que estão no carrinho Além de fornecer o
     * preço total desses produtos com frete
     */
    private void listarCarrinho() {
        Produto produto;
        ProdutoFisico produtoFisico;
        double precoProdutos = 0, precoFrete = 0, precoTotal = 0;
        for (int i = 0; i < produtosNoCarrinho.size(); i++) {
            produto = produtosNoCarrinho.get(i);
            System.out.println(produto.getId() + "  " + produto.getNome() + "  " + produto.getPreco());
            precoProdutos += produto.getPreco();
            if (produto instanceof ProdutoFisico) {
                produtoFisico = (ProdutoFisico) produto;
                precoFrete += produtoFisico.calcularFrete();
            }
        }
        precoTotal = precoFrete + precoProdutos;
        System.out.println("Valor produtos: " + precoProdutos + "\n Valor do frete: " + precoFrete + "\n Valor total: " + precoTotal);
    }

    /**
     * Método para buscar produtos recebendo o id do produto
     */
    private void buscarProdutos() {
        int idProduto;
        ProdutoFisico produtoFisico = null;
        ProdutoVirtual produtoVirtual = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o ID do produto que deseja buscar:");
        idProduto = sc.nextInt();
        sc.nextLine();
        Produto produto = loja.buscarProduto(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
        } else {
            if (produto instanceof ProdutoFisico) {
                produtoFisico = (ProdutoFisico) produto;
            } else {
                produtoVirtual = (ProdutoVirtual) produto;
            }

            System.out.println("ID | Nome | Marca | Descrição | Preço | Categoria | Quantidade | Peso/Formato | Dimensões/Tamanho do Arquivo");
            System.out.print(produto.getId() + " - " + produto.getNome() + " - " + produto.getMarca()
                    + " - " + produto.getDescricao() + " - " + produto.getPreco()
                    + " - " + produto.getNomeCategoria() + " - " + produto.getQuantidade() + " - ");
            if (produtoVirtual != null) {
                System.out.println(produtoVirtual.getFormato() + " - " + produtoVirtual.getTamanhoArquivo());
            } else {
                System.out.println(produtoFisico.getPeso() + " - " + produtoFisico.getDimensoes());
            }
        }

    }

    /**
     * Método para buscar categorias recebendo o código da categoria
     */
    private void buscarCategorias() {
        int codigoCategoria;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o código da categoria que deseja buscar:");
        codigoCategoria = sc.nextInt();
        sc.nextLine();
        Categoria categoria = loja.buscarCategoriaPorCodigo(codigoCategoria);
        if (categoria == null) {
            System.out.println("Categoria não encontrada!");
        } else {
            System.out.println("Código | Nome | Descrição");
            System.out.println(categoria.getCodigo() + " - " + categoria.getNome() + " - " + categoria.getDescricao());
        }

    }

    /**
     * Método para adicionar produto ao carrinho recebendo o id
     */
    private void adicionarAoCarrinho() {
        int idProduto;
        Produto produto;
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o ID do produto que deseja adicionar ao carrinho:");
        idProduto = sc.nextInt();
        sc.nextLine();
        produto = loja.buscarProduto(idProduto);
        if (produto == null) {
            System.out.println("Produto não encontrado!");
        } else {
            if (produto.getQuantidade() <= 0) {
                System.out.println("Produto fora de estoque!");
            } else {
                produtosNoCarrinho.add(produto);
                System.out.println("Produto adicionado ao carrinho com sucesso!");
            }
        }
    }

    /**
     * Método para realizar a compra dos produtos que estão no carrinho Além de
     * dar a opção de baixar o arquivo de algum produto virtual comprado
     */
    private void realizarCompra() {
        ProdutoVirtual produtoVirtual;
        int opcao;
        Scanner sc = new Scanner(System.in);
        if (produtosNoCarrinho == null || produtosNoCarrinho.isEmpty()) {
            System.out.println("Carrinho vazio!");
            return;
        } else {
            for (int i = 0; i < produtosNoCarrinho.size(); i++) {
                Produto produto = produtosNoCarrinho.get(i);
                if (produto.getQuantidade() <= 0) {
                    System.out.println("Produto de id: " + produto.getId() + " fora de estoque!");
                    produtosNoCarrinho.remove(produto);
                } else {
                    produto.venderProduto(produto);
                    if (produto instanceof ProdutoVirtual) {
                        produtoVirtual = (ProdutoVirtual) produto;
                        System.out.println("Deseja Realizar o download do arquivo " + produtoVirtual.getNome() + "?\n 1 - Sim\n 2 - Não");
                        opcao = sc.nextInt();
                        sc.nextLine();
                        if (opcao == 1) {
                            produtoVirtual.realizarDownload();
                        }
                    }
                }
                produtosNoCarrinho.remove(produto);
            }
            System.out.println("Compra realizada com sucesso!");
        }

    }

    /**
     * Método que fornece o menu de opções de gerenciamento das categorias e
     * realiza a opção selecionada
     */
    private void gerenciarCategorias() {
        int opcao, codigoCategoria;
        ArrayList<Categoria> listaCategorias;
        String nomeCategoria, descricaoCategoria;
        Categoria categoria;
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual ação deseja realizar?" + "\n 1 - Alterar nome da categoria" + "\n 2 - Alterar código da categoria"
                + "\n 3 - Alterar descrição da categoria" + "\n 4 - Adicionar categoria" + "\n 5 - Excluir categoria" + "\n 6 - Listar categorias" + "\n 0 - Finalizar");
        opcao = sc.nextInt();
        sc.nextLine();
        switch (opcao) {
            case 1:
                System.out.println("Digite o código da categoria a ser alterada: ");
                codigoCategoria = sc.nextInt();
                sc.nextLine();
                categoria = loja.buscarCategoriaPorCodigo(codigoCategoria);
                if (categoria == null) {
                    System.out.println("Categoria não encontrada!");
                } else {
                    System.out.println("Digite o novo nome da categoria: ");
                    nomeCategoria = sc.nextLine();
                    categoria.setNome(nomeCategoria);
                    System.out.println("Nome alterado com sucesso!");
                }
                break;
            case 2:
                System.out.println("Digite o código da categoria a ser alterada: ");
                codigoCategoria = sc.nextInt();
                sc.nextLine();
                categoria = loja.buscarCategoriaPorCodigo(codigoCategoria);
                if (categoria == null) {
                    System.out.println("Categoria não encontrada!");
                } else {
                    System.out.println("Digite o novo código da categoria: ");
                    codigoCategoria = sc.nextInt();
                    if (loja.buscarCategoriaPorCodigo(codigoCategoria) != null) {
                        System.out.println("O Código inserido já existe!");
                        break;
                    }
                    categoria.setCodigo(codigoCategoria);
                    System.out.println("Código alterado com sucesso!");
                }
                break;
            case 3:
                System.out.println("Digite o código da categoria a ser alterada: ");
                codigoCategoria = sc.nextInt();
                sc.nextLine();
                categoria = loja.buscarCategoriaPorCodigo(codigoCategoria);
                if (categoria == null) {
                    System.out.println("Categoria não encontrada!");
                } else {
                    System.out.println("Digite a nova descrição da categoria: ");
                    descricaoCategoria = sc.nextLine();
                    categoria.setDescricao(descricaoCategoria);
                    System.out.println("Descrição alterada com sucesso!");
                }
                break;
            case 4:
                System.out.println("Digite o nome da nova categoria: ");
                nomeCategoria = sc.nextLine();
                System.out.println("Digite a descricao da nova categoria: ");
                descricaoCategoria = sc.nextLine();
                System.out.println("Digite o codigo da nova categoria: ");
                codigoCategoria = sc.nextInt();
                if (loja.buscarCategoriaPorCodigo(codigoCategoria) != null) {
                    System.out.println("O Código inserido já existe!");
                    break;
                }
                listaCategorias = loja.getCategorias();
                listaCategorias.add(new Categoria(nomeCategoria, descricaoCategoria, codigoCategoria));
                System.out.println("Categoria adicionada com sucesso!");
                break;
            case 5:
                System.out.println("Digite o código da categoria a ser excluída: ");
                codigoCategoria = sc.nextInt();
                sc.nextLine();
                categoria = loja.buscarCategoriaPorCodigo(codigoCategoria);
                if (categoria == null) {
                    System.out.println("Categoria não encontrada!");
                } else {
                    listaCategorias = loja.getCategorias();
                    listaCategorias.remove(categoria);
                    System.out.println("Categoria excluída com sucesso!");
                }
                break;
            case 6:
                listaCategorias = loja.getCategorias();
                if (listaCategorias == null) {
                    System.out.println("Lista de categorias vazia!");
                } else {
                    System.out.println("Código | Nome | Descrição ");
                    for (int i = 0; i < listaCategorias.size(); i++) {
                        categoria = listaCategorias.get(i);
                        System.out.println(categoria.getCodigo() + " - " + categoria.getNome() + " - " + categoria.getDescricao());
                    }
                }
                break;
            default:
                break;

        }
    }

    /**
     * Método que fornece o menu de gerenciamento dos produtos e realiza a opção
     * selecionada
     */
    private void gerenciarProdutos() {
        int opcao, opcaoTipoProduto, idProduto, quantidadeProduto, codigoCategoriaDoProduto, codigoCategoria;
        double precoProduto, pesoProduto, tamanhoArquivoProduto;
        ArrayList<Produto> listaProdutos;
        ArrayList<Categoria> listaCategorias;
        String nomeProduto, descricaoProduto, marcaProduto, nomeCategoriaDoProduto, dimensoesProduto, formatoProduto;
        Produto produto;
        ProdutoFisico produtoFisico = null;
        ProdutoVirtual produtoVirtual = null;
        Categoria novaCategoriaDoProduto, categoria;
        Scanner sc = new Scanner(System.in);
        System.out.println("Qual ação deseja realizar?\n 1 - Alterar nome \n 2 - Alterar ID "
                + "\n 3 - Alterar descrição " + "\n 4 - Alterar marca " + "\n 5 - Alterar preço" + "\n 6 - Alterar quantidade "
                + "\n 7 - Alterar categoria " + "\n 8 - Adicionar produto" + "\n 9 - Excluir produto" + "\n 10 - Listar Produtos" + "\n 0 - Finalizar");
        opcao = sc.nextInt();
        switch (opcao) {
            case 1:
                System.out.println("Digite o ID do produto a ser alterado: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    System.out.println("Digite o novo nome do produto: ");
                    nomeProduto = sc.nextLine();
                    produto.setNome(nomeProduto);
                    System.out.println("Nome do produto alterado com sucesso!");
                }
                break;
            case 2:
                System.out.println("Digite o ID do produto a ser alterado: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    System.out.println("Digite o novo ID do produto: ");
                    idProduto = sc.nextInt();
                    if (loja.buscarProduto(idProduto) != null) {
                        System.out.println("O ID inserido já existe!");
                        break;
                    }
                    produto.setId(idProduto);
                    System.out.println("ID do produto alterado com sucesso!");
                }
                break;
            case 3:
                System.out.println("Digite o ID do produto a ser alterado: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    System.out.println("Digite a nova descrição do produto: ");
                    descricaoProduto = sc.nextLine();
                    produto.setDescricao(descricaoProduto);
                    System.out.println("Descricão do produto alterada com sucesso!");
                }
                break;
            case 4:
                System.out.println("Digite o ID do produto a ser alterado: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    System.out.println("Digite a nova marca do produto: ");
                    marcaProduto = sc.nextLine();
                    produto.setMarca(marcaProduto);
                    System.out.println("Marca do produto alterada com sucesso!");
                }
                break;
            case 5:
                System.out.println("Digite o ID do produto a ser alterado: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    System.out.println("Digite o novo preço do produto: ");
                    precoProduto = sc.nextDouble();
                    sc.nextLine();
                    produto.setPreco(precoProduto);
                    System.out.println("Preço do produto alterado com sucesso!");
                }
                break;
            case 6:
                System.out.println("Digite o ID do produto a ser alterado: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    System.out.println("Digite a nova quantidade do produto: ");
                    quantidadeProduto = sc.nextInt();
                    produto.setQuantidade(quantidadeProduto);
                    System.out.println("Preço do produto alterado com sucesso!");
                }
                break;
            case 7:
                System.out.println("Digite o ID do produto a ser alterado: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    System.out.println("Digite o código da nova categoria do produto: ");
                    codigoCategoria = sc.nextInt();
                    sc.nextLine();
                    novaCategoriaDoProduto = loja.buscarCategoriaPorCodigo(codigoCategoria);
                    if (novaCategoriaDoProduto == null) {
                        System.out.println("Categoria não encontrada!");
                    } else {
                        produto.setCategoria(novaCategoriaDoProduto);
                        System.out.println("Categoria do produto alterada com sucesso!");
                    }
                }
                break;
            case 8:
                System.out.println("Qual o tipo do produto a ser adicionado?\n 1 - Físico \n 2 - Virtual");
                opcaoTipoProduto = sc.nextInt();
                if (opcaoTipoProduto != 1 && opcaoTipoProduto != 2) {
                    System.out.println("Opção inválida!");
                    break;
                }

                sc.nextLine();
                System.out.println("Digite o nome do produto: ");
                nomeProduto = sc.nextLine();
                System.out.println("Digite o ID do produto: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                if (loja.buscarProduto(idProduto) != null) {
                    System.out.println("O ID inserido já existe!");
                    break;
                }
                System.out.println("Digite o preço do produto: ");
                precoProduto = sc.nextDouble();
                sc.nextLine();
                System.out.println("Digite a descrição do produto: ");
                descricaoProduto = sc.nextLine();
                System.out.println("Digite a quantidade do produto: ");
                quantidadeProduto = sc.nextInt();
                sc.nextLine();
                System.out.println("Digite a marca do produto: ");
                marcaProduto = sc.nextLine();
                System.out.println("Digite o código da categoria do produto: ");
                codigoCategoriaDoProduto = sc.nextInt();
                sc.nextLine();
                novaCategoriaDoProduto = loja.buscarCategoriaPorCodigo(codigoCategoriaDoProduto);
                if (novaCategoriaDoProduto == null) {
                    System.out.println("Categoria não encontrada!");
                } else {
                    listaProdutos = novaCategoriaDoProduto.listarProdutos();
                    if (opcaoTipoProduto == 1) {
                        System.out.println("Digite o peso do produto: ");
                        pesoProduto = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Digite as dimnesões do produto: ");
                        dimensoesProduto = sc.nextLine();
                        listaProdutos.add(new ProdutoFisico(idProduto, precoProduto, nomeProduto, descricaoProduto, marcaProduto, novaCategoriaDoProduto, quantidadeProduto, pesoProduto, dimensoesProduto));
                    } else {
                        System.out.println("Digite o tamanho do arquivo:");
                        tamanhoArquivoProduto = sc.nextDouble();
                        sc.nextLine();
                        System.out.println("Digite o formato do arquivo:");
                        formatoProduto = sc.nextLine();
                        listaProdutos.add(new ProdutoVirtual(idProduto, precoProduto, nomeProduto, descricaoProduto, marcaProduto, novaCategoriaDoProduto, quantidadeProduto, tamanhoArquivoProduto, formatoProduto));
                    }
                    System.out.println("Produto cadastrado com sucesso!");
                }
                break;
            case 9:
                System.out.println("Digite o ID do produto a ser removido: ");
                idProduto = sc.nextInt();
                sc.nextLine();
                produto = loja.buscarProduto(idProduto);
                if (produto == null) {
                    System.out.println("Produto não encontrado!");
                } else {
                    novaCategoriaDoProduto = produto.getCategoria();
                    listaProdutos = novaCategoriaDoProduto.listarProdutos();
                    listaProdutos.remove(produto);
                    System.out.println("Produto removido com sucesso!");
                }
                break;
            case 10:
                listaCategorias = loja.getCategorias();
                System.out.println("ID | Nome | Preço | Marca | Quantidade | Categoria | Descrição | Peso/Tamanho | Dimensões/Formato ");
                for (int i = 0; i < listaCategorias.size(); i++) {
                    categoria = listaCategorias.get(i);
                    listaProdutos = categoria.listarProdutos();
                    for (int j = 0; j < listaProdutos.size(); j++) {
                        produto = listaProdutos.get(j);
                        if (produto instanceof ProdutoFisico) {
                            produtoFisico = (ProdutoFisico) produto;
                        } else {
                            produtoVirtual = (ProdutoVirtual) produto;
                        }
                        System.out.print(produto.getId() + " - " + produto.getNome() + " - " + produto.getMarca()
                                + "  " + produto.getQuantidade() + " - " + produto.getNomeCategoria() + " - " + produto.getDescricao());
                        if (produtoFisico != null) {
                            System.out.println(" - " + produtoFisico.getPeso() + " - " + produtoFisico.getDimensoes());
                        } else {
                            System.out.println(" - " + produtoVirtual.getTamanhoArquivo() + " - " + produtoVirtual.getFormato());
                        }
                        produtoFisico = null;
                        produtoVirtual = null;
                    }
                }
                break;
            default:
                break;
        }
    }

}
