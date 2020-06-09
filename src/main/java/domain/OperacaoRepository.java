package domain;

import java.util.List;

public interface OperacaoRepository {

	public List<Operacao> mostraOperacoes();

	public void add(Operacao consumo);

	public String buscaGaveta(Ferramenta fSearch);
	
	public List<String> buscaFerramentas(Gaveta gaveta);

	public List<Operacao> buscarOperacoes(Ferramenta ferramenta, Gaveta gaveta);

	public List<Operacao> buscarOperacoes(Ferramenta ferramenta);

	public List<Operacao> buscarOperacoes(Gaveta gaveta);

	public long getQtdGaveta(Ferramenta ferramenta, Gaveta gaveta);
}
