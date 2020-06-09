package domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Compra")
public class Compra extends Operacional {

	private long quantidade;
	private String nome = "Compra";

	public Compra() {
	}

	public Compra(Date data, Ferramenta ferramenta, Gaveta gaveta, long quantidade) {
		super(data, ferramenta, gaveta);
		this.setQuantidade(quantidade);
	}

	public String toString() {
		return this.nome;
	}

	@Override
	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

}
