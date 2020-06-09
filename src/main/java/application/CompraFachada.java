package application;

import java.util.List;

import domain.ControleCadastro;
import domain.ControleEstoque;
import domain.Ferramenta;
import domain.Gaveta;

public class CompraFachada {

	private ControleEstoque ce;
	private ControleCadastro cc;
	
	public CompraFachada(ControleEstoque ce, ControleCadastro cc) {
		this.ce = ce;
		this.cc = cc;
	}
	
	public List<Ferramenta> getFerramentas(){
		return cc.ferramentasCadastradas();
	}
	
	public List<Gaveta> getGavetas(){
		return cc.gavetasCadastradas();
	}
	
	public void adicionaCompra(Ferramenta ferramenta, Gaveta gaveta, long quantidade) {
		ce.compraFerramenta(ferramenta, gaveta, quantidade);
	}
	public void novaFerramenta(Ferramenta ferramenta) {
		cc.novaFerramenta(ferramenta);
	}
}
