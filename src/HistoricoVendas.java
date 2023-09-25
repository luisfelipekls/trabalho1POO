import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HistoricoVendas {

	private ArrayList<Venda> vendas;

	public HistoricoVendas(){
		this.vendas = new ArrayList<>();
	}

	public boolean insere(Venda venda) {
		return vendas.add(venda);
	}

	public List<Venda> getUltimasVendas(int n) {
		ArrayList<Venda> ultimasVendas = vendas;
		Collections.reverse(ultimasVendas);

		if(n > vendas.size()){
			return ultimasVendas.subList(0, vendas.size() - 1);
		}

		return ultimasVendas.subList(0, n);
	}

	public Venda getVenda(int numero) {

		for(Venda v: vendas){
			if(v.getNumero() == numero) {
				return v;
			}
		}

		return null;
	}

}
