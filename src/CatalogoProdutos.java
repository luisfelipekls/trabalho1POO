import java.util.ArrayList;

public class CatalogoProdutos {

	private ArrayList<Produto> produtos;

	public CatalogoProdutos(){
		this.produtos = new ArrayList<>();
	}

	public Produto consulta(int codigo) {
		for(Produto p: produtos){
			if(p.getCodigo() == codigo) {
				System.out.println("Produto disponível!");
				return p;
			}
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
