import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Venda {

	private int numero;

	private List<ItemVenda> itens;


	public Venda(int numero){
		this.itens = new ArrayList<>();
		this.numero = numero;
	}

	public int getNumero() {
		return numero;
	}

	public double getSubtotal() {
		double subtotal = 0;
		for (ItemVenda itemVenda: itens) {
			subtotal+=itemVenda.getPrecoUnitarioCobrado()*itemVenda.getQuantidade();
		}
		return subtotal;
	}

	public double getDesconto() {
		if(this.getSubtotal()>=250.0){
			return this.getSubtotal()*0.10;
		}
		return 0;
	}

	public double getImposto() {
		return this.getSubtotal()*0.25;
	}

	public double getTotalVenda() {
		return (this.getSubtotal() - this.getDesconto()) + this.getImposto();
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

	public void imprimeRecibo() {
		System.out.println("--------------------------------------");
		System.out.println("Recibo: ");

        for (ItemVenda i : itens) {
            System.out.println(i);
        }

		System.out.println();
		System.out.println("Subtotal: R$" + getSubtotal());
		System.out.println("Desconto: R$" + getDesconto());
		System.out.println("Imposto: R$" + getImposto());
		System.out.println("Total: R$" + getTotalVenda());
	}

	public boolean fecha(){
        return !itens.isEmpty();
    }

	public List<ItemVenda> getItens() {
		return itens;
	}

	@Override
	public String toString() {
		return "\nVenda #"  + this.numero +
				"\nItens: " + itens;
	}
}
