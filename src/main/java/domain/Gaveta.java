package domain;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Gaveta {
	@Id
	private Integer id;

	private String nome;

	@Transient
	private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

	public Gaveta() {
	}

	public Gaveta(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public void addPropertyChangeListener(PropertyChangeListener x) {
		changeSupport.addPropertyChangeListener(x);
	}

	public void removePropertyChangeListener(PropertyChangeListener x) {
		changeSupport.removePropertyChangeListener(x);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		Object oldValue = this.id;
		this.id = id;
		changeSupport.firePropertyChange("id", oldValue, this.id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		Object oldValue = this.nome;
		this.nome = nome;
		changeSupport.firePropertyChange("nome", oldValue, this.nome);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Gaveta other = (Gaveta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
