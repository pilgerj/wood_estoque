package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControleEstoque {

	private OperacaoRepository operacaoRepository;
	private EstoqueRepository estoqueRepository;

	public ControleEstoque() {
	}

	public ControleEstoque(OperacaoRepository operacaoRepository, EstoqueRepository estoqueRepository) {
		this.operacaoRepository = operacaoRepository;
		this.estoqueRepository = estoqueRepository;
	}

	public void getFerramentas() {
		estoqueRepository.getFerramentas();
	}
	
	
	public void compraFerramenta(Ferramenta ferramenta, Gaveta gaveta, long qtd) {
		operacaoRepository.add(new Compra(new Date(), ferramenta, gaveta, qtd));
	}

	public void consumoFerramenta(Ferramenta ferramenta, Gaveta gaveta, long qtd) {
		operacaoRepository.add(new Consumo(new Date(), ferramenta, gaveta, qtd));
	}

	public void vendaFerramenta(Ferramenta ferramenta, Gaveta gaveta, long quantidade) {
		if (getQtdGaveta(ferramenta, gaveta) >= quantidade && getTotalFerramenta(ferramenta) != 0)
			operacaoRepository.add(new Venda(new Date(), ferramenta, gaveta, quantidade));

	}

	public boolean transferirProduto(Gaveta gavetaSaida, Ferramenta ferramenta, Gaveta gavetaEntrada, long quantidade) {
		if (operacaoRepository.getQtdGaveta(ferramenta, gavetaSaida) >= quantidade) {
			consumoFerramenta(ferramenta, gavetaSaida, quantidade);
			compraFerramenta(ferramenta, gavetaEntrada, quantidade);
			return true;
		} else {
			return false;
		}
	}

	public long getTotalFerramentaGaveta(Gaveta gaveta) {
		List<Operacao> operacoes = operacaoRepository.buscarOperacoes(gaveta);
		long qtd = 0;
		for (Operacao op : operacoes) {
			qtd = qtd + op.getQuantidade();
		}
		return qtd;
	}

	

	public String buscaGaveta(Ferramenta fSearch) {
		return operacaoRepository.buscaGaveta(fSearch);
	}

	// public long getQuantidade(Ferramenta ferramenta) {
	// //return gavetas.stream().flatMap(g -> g.getFerramentas().stream()).filter(f
	// -> f.equals(ferramenta)).count();

	public long getQtdGaveta(Ferramenta ferramenta, Gaveta gaveta) {
		return operacaoRepository.getQtdGaveta(ferramenta, gaveta);
	}

	public long getTotalFerramenta(Ferramenta ferramenta) {
		List<Operacao> operacoes = operacaoRepository.buscarOperacoes(ferramenta);
		long qtd = 0;
		for (Operacao op : operacoes) {
			qtd = qtd + op.getQuantidade();
		}
		return qtd;
	}

	public List<Operacao> mostraOperacoes() {
		List<Operacao> filtraoperacoes = new ArrayList<>();
		filtraoperacoes.addAll(operacaoRepository.mostraOperacoes());
		// for(Operacao o : operacaoRepository.mostraOperacoes()) {
		// System.out.println("----------");
		// System.out.println(o.toString());
		// System.out.println(o.getFerramenta().getNome());
		// System.out.println(o.getGaveta().getNome());
		// System.out.println(o.getQuantidade());
		// System.out.println(o.getDataAcao());
		// System.out.println("----------");
		// }
		return filtraoperacoes;
	}

	public List<Operacao> listaOperacoesFerramenta(Ferramenta f) {
		List<Operacao> filtraoperacoes = new ArrayList<>();
		List<Operacao> operacoes = operacaoRepository.buscarOperacoes(f);
		for (Operacao op : operacoes) {
			if (op.getFerramenta().equals(f)) {
				filtraoperacoes.add(op);
			}
		}
		return filtraoperacoes;
	}
	
	

}
