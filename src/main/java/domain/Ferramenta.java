package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Ferramenta {

	@Id
	private Integer id;
	private String nome;

	@Transient
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public Ferramenta() {
	}

	public Ferramenta(String n, Integer id) {

		this.nome = n;
		this.id = id;

	}

	public void addPropertyChangeListener(PropertyChangeListener x) {
		changeSupport.addPropertyChangeListener(x);
	}

	public void removePropertyChangeListener(PropertyChangeListener x) {
		changeSupport.removePropertyChangeListener(x);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		Object oldValue = this.nome;
		this.nome = nome;
		changeSupport.firePropertyChange("nome", oldValue, this.nome);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		Object oldValue = this.id;
		this.id = id;
		changeSupport.firePropertyChange("id", oldValue, this.id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ferramenta other = (Ferramenta) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
