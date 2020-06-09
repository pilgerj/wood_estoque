package domain;

import java.util.List;

public class ControleCadastro {

	private EstoqueRepository estoqueRepository;

	public ControleCadastro(EstoqueRepository estoqueRepository) {
		this.estoqueRepository = estoqueRepository;
	}

	public void saveGaveta(Gaveta gaveta) {
		estoqueRepository.addGaveta(gaveta);
	}

	public boolean verificaFerramenta(Ferramenta ferramenta) {
		for (Ferramenta f : estoqueRepository.getFerramentas()) {
			if(f.getId().equals(ferramenta.getId())) {
				return true;	
			}
		}
		return false;
	}

	public void novaFerramenta(Ferramenta ferramenta) {
		estoqueRepository.addFerramenta(ferramenta);
	}

	public List<Ferramenta> ferramentasCadastradas() {
		return estoqueRepository.getFerramentas();
	}

	public List<Gaveta> gavetasCadastradas() {
		return estoqueRepository.getGavetas();
	}


	public boolean verificaGaveta(Gaveta gaveta) {
		for (Gaveta g : estoqueRepository.getGavetas()) {
			if (g.getId().equals(gaveta.getId())) {
				return true;
			}
		}
		return false;
	}
}
