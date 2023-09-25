import java.util.ArrayList;

public class CatalogoProdutos {

	private ArrayList<Produto> produtos;

	public Produto consulta(int codigo) {
		for(Produto produto: produtos){
			if(produto.getCodigo() == codigo) return produto;
		}

		return null;
	}

	public boolean cadastra(Produto produto) {
		for(Produto p: produtos){
			if(produto.getCodigo() == p.getCodigo()) return false;
		}

		produtos.add(produto);
		return true;
	}

}
