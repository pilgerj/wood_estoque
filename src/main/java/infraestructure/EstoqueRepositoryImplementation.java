package infraestructure;

import java.util.List;

import domain.EstoqueRepository;
import domain.Ferramenta;
import domain.Gaveta;

public class EstoqueRepositoryImplementation implements EstoqueRepository  {

	private EstoqueDAO estoqueDAO;
	
	public EstoqueRepositoryImplementation(EstoqueDAO estoqueDAO) {
		this.estoqueDAO = estoqueDAO;
	}

	@Override
	public List<Ferramenta> getFerramentas() {
		return estoqueDAO.getFerramentas();
	}

	@Override
	public void addFerramenta(Ferramenta ferramenta) {
		estoqueDAO.addFerramenta(ferramenta);
	}

	@Override
	public List<Gaveta> getGavetas() {
		return estoqueDAO.getGavetas();
	}

	@Override
	public void addGaveta(Gaveta gaveta) {
		estoqueDAO.addGaveta(gaveta);
	}

}
