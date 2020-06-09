package ui;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Produto {
	
	private Integer codigo;
	private String descricao;
	
	private transient PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);
	
	public void addPropertyChangeListener(PropertyChangeListener l) {
		changeSupport.addPropertyChangeListener(l);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener l) {
		changeSupport.removePropertyChangeListener(l);
	}
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		Object oldValue = this.codigo;
		this.codigo = codigo;
		changeSupport.firePropertyChange("codigo", oldValue, codigo);
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		Object oldValue = this.descricao;
		this.descricao = descricao;
		changeSupport.firePropertyChange("descricao", oldValue, descricao);
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao + "]";
	}

	
}
