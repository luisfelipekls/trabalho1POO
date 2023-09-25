import java.util.ArrayList;

public class Estoque {

	private ArrayList<ItemEstoque> itens;

	public int getQuantidade(int codigo) {
		int quantidadeItem = 0;

		for(ItemEstoque item : itens){
			if(item.getProduto().getCodigo() == codigo){
				quantidadeItem = item.getQuantidade();
			}
		}
		return quantidadeItem;
	}

	public boolean baixaEstoque(int codigo, int quantidade) {
		for(ItemEstoque item : itens){
			if(item.getProduto().getCodigo() == codigo){
				int quantidadeAlterada = item.getQuantidade() - quantidade;

				if(quantidadeAlterada >= 0){
					item.atualizaQuantidade(quantidadeAlterada);
					return true;
				}else{
					System.out.println("A quantidade a ser removida é maior que a quantidade mantida em estoque!");
				}

			}
		}
		return false;
	}

	public boolean repoeEstoque(int codigo, int quantidade) {
		for(ItemEstoque item : itens){
			if(item.getProduto().getCodigo() == codigo){
				int quantidadeAlterada = item.getQuantidade() + quantidade;

				item.atualizaQuantidade(quantidadeAlterada);
				return true;
			}
		}
		return false;
	}

}
