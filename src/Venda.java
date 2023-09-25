import java.util.List;

public class Venda {

	private int numero;

	private List<ItemVenda> itens;

	public int getNumero() {
		return numero;
	}

	public double getSubtotal() {
		double subtotal = 0;
		for (ItemVenda itemVenda: itens) {
			subtotal+=itemVenda.getValorItem()*itemVenda.getQuantidade();
		}
		return subtotal;
	}

	public double getDesconto() {
		if(this.getTotalVenda()>=250.0){
			return this.getSubtotal()*0.10;
		}
		return 0;
	}

	public double getImposto() {
		return this.getSubtotal()*0.10;
	}

	public double getTotalVenda() {
		return this.getSubtotal()+this.getImposto();
	}

	public void insereItem(int numero,Produto produto, int quantidade) {
		ItemVenda novoItem = new ItemVenda(numero, produto, produto.getPrecoUnitario(), quantidade);
		itens.add(novoItem);
	}

	public boolean removeItem(int numero) {
		int numeroInicialDeItens = itens.size();
		itens.removeIf(itemVenda -> itemVenda.getNumero() == numero);
		return numeroInicialDeItens > itens.size();
    }

	public boolean imprimeRecibo() {
		return false;
	}

}
