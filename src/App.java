import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int cont = 1;
        int numeroVenda = 1;

        Produto geladeira = new Produto(1233, "Geladeira", 2500);
        Produto ventilador = new Produto(4461, "Ventilador", 147);
        Produto fogao = new Produto(6765, "Fogão", 879);
        Produto ferro = new Produto(3943, "Ferro de Passar", 225);

        ItemEstoque estoqueGeladeira = new ItemEstoque(geladeira, 20);
        ItemEstoque estoqueVentilador = new ItemEstoque(ventilador, 20);
        ItemEstoque estoqueFogao = new ItemEstoque(fogao, 20);
        ItemEstoque estoqueFerro = new ItemEstoque(ferro, 20);

        Estoque e = new Estoque();

        e.adicionaNoEstoque(estoqueGeladeira);
        e.adicionaNoEstoque(estoqueVentilador);
        e.adicionaNoEstoque(estoqueFogao);
        e.adicionaNoEstoque(estoqueFerro);

        CatalogoProdutos c = new CatalogoProdutos();
        c.cadastra(geladeira);
        c.cadastra(ventilador);
        c.cadastra(fogao);
        c.cadastra(ferro);

        HistoricoVendas h = new HistoricoVendas();

        boolean emAndamento = true;

        while(emAndamento){

            System.out.println();
            menu();
            int opcao = input.nextInt();

            switch(opcao){
                case 1:
                    Venda v = new Venda(numeroVenda);

                    boolean vendendo = true;

                    while(vendendo) {

                        imprimeProdutos(geladeira, ventilador, fogao, ferro);

                        System.out.println();

                        System.out.println("O que deseja fazer?");
                        System.out.println("(1) Adicionar produto");
                        System.out.println("(2) Remover produto");
                        System.out.println("(3) Fechar venda");
                        System.out.print("Escolha: ");
                        int opcaoVenda = input.nextInt();

                        switch (opcaoVenda) {
                            case 1:
                                System.out.print("Código do produto: ");
                                int codigo = input.nextInt();
                                System.out.print("Quantidade: ");
                                int quantidade = input.nextInt();

                                boolean foiAdicionado = false;

                                for (ItemEstoque i : e.getItens()) {
                                    if (i.getProduto().getCodigo() == codigo) {
                                        v.insereItem(cont, i.getProduto(), quantidade);
                                        cont++;
                                        foiAdicionado = true;
                                        System.out.println("Produto adicionado!");
                                        break;
                                    }
                                }
                                if(foiAdicionado) {
                                    break;
                                } else {
                                    System.out.println("Produto fora do catálogo.");
                                    break;
                                }

                            case 2:
                                System.out.print("Número do produto na lista: ");
                                int numeroRemocao = input.nextInt();

                                for(ItemVenda i : v.getItens()){
                                    if(i.getNumero() == numeroRemocao) {
                                        v.removeItem(numeroRemocao);
                                        System.out.println("Item " + numeroRemocao + " removido!");
                                        e.repoeEstoque(i.getProduto().getCodigo(), i.getQuantidade());
                                    }

                                    if(i.getNumero() > numeroRemocao){
                                        i = new ItemVenda(i.getNumero()-1, i.getProduto(),
                                                i.getPrecoUnitarioCobrado(), i.getQuantidade());
                                    }
                                }

                                break;



                            case 3:
                                h.insere(v);
                                v.fecha();

                                if (v.fecha()) {
                                    for(ItemVenda i : v.getItens()){
                                        e.baixaEstoque(i.getProduto().getCodigo(), i.getQuantidade());
                                    }

                                    System.out.println("Venda finalizada!");
                                    v.imprimeRecibo();
                                    cont = 1;
                                    numeroVenda++;
                                    vendendo = false;
                                    break;

                                } else {
                                    System.out.println("Nenhum produto foi inserido.");
                                }

                                break;

                            default:
                                System.out.println("Opção inválida.");
                                break;
                        }

                    }

                    break;

                case 2:
                    imprimeProdutos(geladeira, ventilador, fogao, ferro);
                    System.out.print("Informe o código do produto: ");
                    int codigoConsulta = input.nextInt();

                    c.consulta(codigoConsulta);
                    break;

                case 3:
                    imprimeProdutos(geladeira, ventilador, fogao, ferro);
                    System.out.print("Informe o código do produto: ");
                    int codigoConfeccao = input.nextInt();

                    boolean foiConferido = false;

                    for(ItemEstoque i : e.getItens()){
                        if(i.getProduto().getCodigo() == codigoConfeccao){
                            if(i.getQuantidade() <= 0){
                                System.out.println("Produto fora do estoque.");
                                break;
                            }

                            System.out.println("Quantidade no estoque: " + i.getQuantidade() + ".");
                            foiConferido = true;
                            break;
                        }
                    }

                    if(!foiConferido) {
                        System.out.println("Produto não encontrado.");
                    }
                    break;

                case 4:
                    imprimeProdutos(geladeira, ventilador, fogao, ferro);
                    System.out.print("Informe o código do produto: ");
                    int codigoReposicao = input.nextInt();

                    boolean foiAdicionado = false;

                    for(ItemEstoque i : e.getItens()){
                        if(i.getProduto().getCodigo() == codigoReposicao){
                            System.out.print("Quantidade de produtos reposta: ");
                            int quantidadeReposicao = input.nextInt();

                            e.repoeEstoque(codigoReposicao, quantidadeReposicao);
                            foiAdicionado = true;
                            System.out.println("Produto reposto!");
                            break;
                        }
                    }

                    if(!foiAdicionado){
                        System.out.println("Produto não encontrado.");
                        break;
                    }

                    break;

                case 5:

                    System.out.print("Quantas vendas deseja ver?: ");
                    int ultimasVendas = input.nextInt();

                    System.out.println("\n" + h.getUltimasVendas(ultimasVendas));
                    break;


                case 6:

                    if(numeroVenda <= 1){
                        System.out.println("Nenhuma venda feita até o momento.");
                        break;
                    }

                    System.out.println("Venda totais: " + (numeroVenda-1));
                    System.out.print("Qual venda deseja acessar?: ");
                    int vendaEspecifica = input.nextInt();

                    System.out.println(h.getVenda(vendaEspecifica));
                    break;

                default:
                    emAndamento = false;
            }

        }

    }

    public static void menu(){

        System.out.println("----------------------------------------");
        System.out.println("(1) Abrir uma venda");
        System.out.println("(2) Ver disponibilidade de um produto");
        System.out.println("(3) Conferir estoque");
        System.out.println("(4) Repor Estoque");
        System.out.println("(5) Imprimir últimas vendas");
        System.out.println("(6) Ver venda específica");
        System.out.println("(7) Sair");
        System.out.print("Escolha: ");

    }

    public static void imprimeProdutos(Produto p1, Produto p2, Produto p3, Produto p4){

        System.out.println("----------------------------------------");
        System.out.println("Produtos: ");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println("----------------------------------------");

    }

}
