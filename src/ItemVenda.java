import java.util.Random;

public class ItemVenda {

	private int numero;

	private Produto produto;

	private double precoUnitarioCobrado;

	private int quantidade;

	public ItemVenda(int numero, Produto produto, double precoUnitarioCobrado, int quantidade) {
		this.numero = numero;
		this.produto = produto;
		this.precoUnitarioCobrado = precoUnitarioCobrado;
		this.quantidade = quantidade;
	}

	public int getNumero() {
		return numero;
	}

	public Produto getProduto() {
		return produto;
	}

	public double getPrecoUnitarioCobrado() {
		return precoUnitarioCobrado;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getValorItem() {
		return precoUnitarioCobrado * quantidade;
	}

	public String toString(){
		return "Produto: " + this.getProduto() +
				", Quantidade: " + this.getQuantidade() +
				"\n";
	}

}
