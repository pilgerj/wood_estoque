package infraestructure;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import domain.Ferramenta;
import domain.Gaveta;
import domain.Operacao;

public class OperacaoMemoryDAO implements OperacaoDAO{

	private List<Operacao> operacoes = new ArrayList<>();
	
	@Override
	public List<Operacao> mostraOperacoes() {
		List<Operacao> todasOperacoes = new ArrayList<>();
		todasOperacoes.addAll(operacoes);
		return todasOperacoes;
	}

	@Override
	public List<String> buscaFerramentas(Gaveta gaveta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(Operacao operacao) {
		operacoes.add(operacao);
	}

	@Override
	public String buscaGaveta(Ferramenta ferramenta) {
		for (Operacao op : operacoes) {
			if (getQtdGaveta(ferramenta, op.getGaveta()) > 0)
				return op.getGaveta().getNome();
		}
		return null;
	}
	@Override
	public List<Operacao> buscarOperacoes(Ferramenta ferramenta, Gaveta gaveta) {
		return operacoes.stream().filter(o -> o.getFerramenta().equals(ferramenta))
				.filter(o -> o.getGaveta().equals(gaveta)).collect(Collectors.toList());
	}

	@Override
	public List<Operacao> buscarOperacoes(Ferramenta ferramenta) {
		return operacoes.stream().filter(o -> o.getFerramenta().equals(ferramenta)).collect(Collectors.toList());
	}

	@Override
	public List<Operacao> buscarOperacoes(Gaveta gaveta) {
		return operacoes.stream().filter(o -> o.getGaveta().equals(gaveta)).collect(Collectors.toList());
	}

	@Override
	public long getQtdGaveta(Ferramenta ferramenta, Gaveta gaveta) {
		long qtdTotal = 0;
		for (Operacao op : buscarOperacoes(ferramenta, gaveta)) {
			qtdTotal += op.getQuantidade();
		}
		return qtdTotal;

	}



}
