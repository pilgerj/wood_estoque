package infraestructure;

import java.util.List;

import domain.Ferramenta;
import domain.Gaveta;
import domain.Operacao;
import domain.OperacaoRepository;

public class OperacaoRepositoryImplementation implements OperacaoRepository{

	private OperacaoDAO operacaoDAO;

	public OperacaoRepositoryImplementation(OperacaoDAO operacaoDAO) {
		this.operacaoDAO = operacaoDAO;
	}
	@Override
	public List<Operacao> mostraOperacoes() {
		return operacaoDAO.mostraOperacoes();
	}
	
	@Override
	public List<String> buscaFerramentas(Gaveta gaveta) {
		return operacaoDAO.buscaFerramentas(gaveta);
	}

	public void add(Operacao consumo) {
		operacaoDAO.add(consumo);
	}

	public String buscaGaveta(Ferramenta fSearch) {
		return operacaoDAO.buscaGaveta(fSearch);
	}

	public List<Operacao> buscarOperacoes(Ferramenta ferramenta, Gaveta gaveta) {
		return operacaoDAO.buscarOperacoes(ferramenta, gaveta);
	}

	public List<Operacao> buscarOperacoes(Ferramenta ferramenta) {
		return operacaoDAO.buscarOperacoes(ferramenta);
	}

	public List<Operacao> buscarOperacoes(Gaveta gaveta) {
		return operacaoDAO.buscarOperacoes(gaveta);
	}

	public long getQtdGaveta(Ferramenta ferramenta, Gaveta gaveta) {
		return operacaoDAO.getQtdGaveta(ferramenta, gaveta);
	}
	

}
