package application;

import java.util.List;

import domain.ControleCadastro;
import domain.ControleEstoque;
import domain.Ferramenta;
import domain.Gaveta;

public class VendaFachada {

	private ControleEstoque ce;
	private ControleCadastro cc;

	public VendaFachada(ControleEstoque ce, ControleCadastro cc) {
		this.ce = ce;
		this.cc = cc;
	}

	public List<Ferramenta> getFerramentas() {
		return cc.ferramentasCadastradas();
	}

	public List<Gaveta> getGavetas() {
		return cc.gavetasCadastradas();
	}

	public void vendaFerramenta(Ferramenta ferramenta, Gaveta gaveta, long quantidade) {
		ce.vendaFerramenta(ferramenta, gaveta, quantidade);
	}

	public long getTotalFerramentaGaveta(Gaveta gaveta) {
		return ce.getTotalFerramentaGaveta(gaveta);
	}

	public String buscaGaveta(Ferramenta fSearch) {
		return ce.buscaGaveta(fSearch);
	}

	public long getTotalFerramenta(Ferramenta ferramenta) {
		return ce.getTotalFerramenta(ferramenta);
	}
	
	public long getQtdGaveta(Ferramenta ferramenta, Gaveta gaveta) {
		return ce.getQtdGaveta(ferramenta, gaveta);
	}

}
