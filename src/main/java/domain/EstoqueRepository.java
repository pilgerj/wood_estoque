package domain;

import java.util.List;

public interface EstoqueRepository {

	public List<Ferramenta> getFerramentas();
	
	public void addFerramenta(Ferramenta ferramenta);

	public List<Gaveta> getGavetas();

	public void addGaveta(Gaveta gaveta);
	
}	
