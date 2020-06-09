package domain;

import java.util.Date;

public interface Operacao 
{

	
	
	public Date getDataAcao();

	public Gaveta getGaveta();

	public Ferramenta getFerramenta();

	public long getQuantidade();
	
	public String toString();
	
	

}