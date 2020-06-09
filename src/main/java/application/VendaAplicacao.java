package application;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import domain.Ferramenta;
import domain.Gaveta;

public class VendaAplicacao {

	private Ferramenta ferramenta;
	private Gaveta gaveta;
	private long quantidade;

	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public VendaAplicacao() {

	}

	public void addPropertyChangeListener(PropertyChangeListener x) {
		changeSupport.addPropertyChangeListener(x);
	}

	public void removePropertyChangeListener(PropertyChangeListener x) {
		changeSupport.removePropertyChangeListener(x);
	}

	public Ferramenta getFerramenta() {
		return ferramenta;
	}

	public void setFerramenta(Ferramenta ferramenta) {
		Object oldValue = this.ferramenta;
		this.ferramenta = ferramenta;
		changeSupport.firePropertyChange("ferramenta", oldValue, this.ferramenta);
	}

	public Gaveta getGaveta() {
		return gaveta;
	}

	public void setGaveta(Gaveta gaveta) {
		Object oldValue = this.gaveta;
		this.gaveta = gaveta;
		changeSupport.firePropertyChange("gaveta", oldValue, this.gaveta);
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		Object oldValue = this.quantidade;
		this.quantidade = quantidade;
		changeSupport.firePropertyChange("quantidade", oldValue, this.quantidade);
	}

}
