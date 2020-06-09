package application;

import java.util.List;

import domain.ControleCadastro;
import domain.ControleEstoque;
import domain.Ferramenta;
import domain.Gaveta;
import domain.Operacao;

public class OperacoesFachada {

	private ControleEstoque ce;
	private ControleCadastro cc;

	public OperacoesFachada(ControleEstoque ce, ControleCadastro cc) {
		this.ce = ce;
		this.cc = cc;
	}

	public List<Ferramenta> getFerramentas() {
		return cc.ferramentasCadastradas();
	}

	public List<Gaveta> getGavetas() {
		return cc.gavetasCadastradas();
	}

	public List<Operacao> mostraOperacoes() {
		return ce.mostraOperacoes();
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
