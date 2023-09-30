public class Main {
    public static void main(String[] args) {

        /**
         * Inicializando Produtos.
         */

        Produto geladeira = new Produto(1233, "Geladeira", 2500);
        Produto ventilador = new Produto(4461, "Ventilador", 147);
        Produto fogao = new Produto(6765, "Fogão", 879);
        Produto ferro = new Produto(3943, "Ferro de Passar", 225);

        /**
         * Inicializando produtos no estoque e atualização de quantidade.
         */

        ItemEstoque estoqueGeladeira = new ItemEstoque(geladeira, 10);
        ItemEstoque estoqueVentilador = new ItemEstoque(ventilador, 12);
        ItemEstoque estoqueFogao = new ItemEstoque(fogao, 10);
        ItemEstoque estoqueFerro = new ItemEstoque(ferro, 10);

        estoqueFogao.atualizaQuantidade(15);
        System.out.println("'Fogão' em Estoque: " + estoqueFogao.getQuantidade()); //Anterior: 10

        /**
         * Inicializando Estoque e adicionando itens; reposição e baixa.
         */

        Estoque e = new Estoque();

        e.adicionaNoEstoque(estoqueGeladeira);
        e.adicionaNoEstoque(estoqueVentilador);
        e.adicionaNoEstoque(estoqueFogao);
        e.adicionaNoEstoque(estoqueFerro);

        System.out.println("'Ventilador' em Estoque: " + e.getQuantidade(4461));

        e.repoeEstoque(3943, 10);
        System.out.println("'Ferro de Passar' em Estoque: " + e.getQuantidade(3943)); //Anterior: 10

        e.baixaEstoque(1233, 5);
        System.out.println("'Geladeira' em Estoque: " + e.getQuantidade(1233)); //Anterior: 10

        /**
         * Inicializando catálogo e testando métodos de cadastro e consulta.
         */

        CatalogoProdutos c = new CatalogoProdutos();
        c.cadastra(geladeira);
        c.cadastra(ventilador);
        c.cadastra(fogao);
        c.cadastra(ferro);

        System.out.println("----------------------------------------------------------");

        System.out.println(c.consulta(6765)); //Pesquisando fogão no catálogo.

        /**
         * Venda teste armazenada; inicializando Histórico.
         */

        HistoricoVendas h = new HistoricoVendas();

        Venda vArmazenada = new Venda(1);

        vArmazenada.insereItem(01, geladeira, 2);
        vArmazenada.insereItem(02, ventilador, 5);
        vArmazenada.insereItem(03, fogao, 2);
        vArmazenada.insereItem(04, ferro, 3);

        h.insere(vArmazenada);

        e.baixaEstoque(1233,2);
        e.baixaEstoque(4461, 5);
        e.baixaEstoque(6765, 2);
        e.baixaEstoque(3943, 3);

        /**
         * Inicialização da venda.
         */


        System.out.println("---------------------------------------------------------");
        System.out.println("Venda: ");
        Venda v = new Venda(2);

        v.insereItem(01, geladeira, 1);
        v.insereItem(02, ventilador, 3);
        v.insereItem(03, fogao, 1);
        v.insereItem(04, ferro, 4);

        v.removeItem(01); //Removendo geladeira.

        System.out.println(v.getSubtotal()); //esperado: 2220
        System.out.println(v.getDesconto()); //esperado: 222
        System.out.println(v.getImposto()); //esperado: 555
        System.out.println(v.getTotalVenda()); //esperado: 2553

        /**
         * Imprimindo recibo e baixando estoque.
         */

        v.imprimeRecibo();

        e.baixaEstoque(4461, 3);
        e.baixaEstoque(6765, 1);
        e.baixaEstoque(3943, 4);

        System.out.println("---------------------------------------------------------");
        System.out.println("Quantidade de cada item no Estoque após as vendas: ");
        System.out.println("Geladeira: " + estoqueGeladeira.getQuantidade()); //Nenhuma vendida.
        System.out.println("Ventilador: " + estoqueVentilador.getQuantidade()); //3 vendidos.
        System.out.println("Fogão: " + estoqueFogao.getQuantidade()); //1 vendido.
        System.out.println("Ferro de Passar: " + estoqueFerro.getQuantidade()); //4 vendidos.

        /**
         * Inicializando histórico, finalizando e imprimindo vendas.
         */

        System.out.println("---------------------------------------------------------");

        if(v.fecha())
            h.insere(v);

        System.out.println("Histórico de Vendas: ");
        h.getUltimasVendas(10).forEach((venda -> {
            venda.imprimeRecibo();
            System.out.println("--------------------------------------");
        }));

    }
}