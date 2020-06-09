package infraestructure;

import java.util.List;

import domain.Ferramenta;
import domain.Gaveta;

public interface EstoqueDAO {

	public List<Ferramenta> getFerramentas();

	public void addFerramenta(Ferramenta ferramenta);

	public List<Gaveta> getGavetas();

	public void addGaveta(Gaveta gaveta);

}
