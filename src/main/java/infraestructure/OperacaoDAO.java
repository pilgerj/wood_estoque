package infraestructure;

import java.util.List;

import domain.Ferramenta;
import domain.Gaveta;
import domain.Operacao;

public interface OperacaoDAO {

	public List<Operacao> mostraOperacoes();

	public List<String> buscaFerramentas(Gaveta gaveta);

	public void add(Operacao operacao);

	public String buscaGaveta(Ferramenta fSearch);

	public List<Operacao> buscarOperacoes(Ferramenta ferramenta, Gaveta gaveta);

	public List<Operacao> buscarOperacoes(Ferramenta ferramenta);

	public List<Operacao> buscarOperacoes(Gaveta gaveta);

	public long getQtdGaveta(Ferramenta ferramenta, Gaveta gaveta);

}
