package domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Venda")
public class Venda extends Operacional {

	private long quantidade;
	private String nome = "Venda";

	public Venda() {
	}

	public Venda(Date date, Ferramenta ferramenta, Gaveta gaveta, long quantidade) {
		super(date, ferramenta, gaveta);
		this.quantidade = quantidade;

	}

	public String toString() {
		return this.nome;
	}

	@Override
	public long getQuantidade() {
		return -quantidade;
	}

}
