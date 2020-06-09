package domain;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value = "Consumo")
public class Consumo extends Operacional {

	private long quantidade;
	private static String nome = "Consumo";

	public Consumo() {
	}

	public Consumo(Date data, Ferramenta ferramenta, Gaveta gaveta, long quantidade) {
		super(data, ferramenta, gaveta);
		this.quantidade = quantidade;
	}

	@SuppressWarnings("static-access")
	public String toString() {
		return this.nome;
	}

	@Override
	public long getQuantidade() {
		return -quantidade;
	}

	public static String getNome() {
		return nome;
	}

}
