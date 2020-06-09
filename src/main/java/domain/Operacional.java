package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Operacao")
@Access(AccessType.FIELD)
public abstract class Operacional implements Operacao {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	protected long codigo;
	@Column(name = "data_acao")	
	protected Date dataAcao;
	@ManyToOne
	protected Gaveta gaveta;
	@ManyToOne
	protected Ferramenta ferramenta;

	public Operacional() {
	}

	public Operacional(Date data, Ferramenta ferramenta, Gaveta gaveta) {
		this.dataAcao = data;
		this.ferramenta = ferramenta;
		this.gaveta = gaveta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teste.Operacao#getDataAcao()
	 */
	@Override
	public Date getDataAcao() {
		return dataAcao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teste.Operacao#getGaveta()
	 */
	@Override
	public Gaveta getGaveta() {
		return gaveta;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see teste.Operacao#getFerramenta()
	 */
	@Override
	public Ferramenta getFerramenta() {
		return ferramenta;
	}

}
