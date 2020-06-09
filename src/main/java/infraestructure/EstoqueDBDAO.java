package infraestructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import domain.Ferramenta;
import domain.Gaveta;
import util.JPAUtil;

public class EstoqueDBDAO implements EstoqueDAO {

	@Override
	public List<Ferramenta> getFerramentas() {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT f FROM Ferramenta f";
		TypedQuery<Ferramenta> query = em.createQuery(jpql, Ferramenta.class);
		return query.getResultList();
	}

	@Override
	public void addFerramenta(Ferramenta ferramenta) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(ferramenta);
		em.getTransaction().commit();
		em.clear();
	}

	@Override
	public List<Gaveta> getGavetas() {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT g FROM Gaveta g";
		TypedQuery<Gaveta> query = em.createQuery(jpql, Gaveta.class);

		return query.getResultList();
	}

	@Override
	public void addGaveta(Gaveta gaveta) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.persist(gaveta);
		em.getTransaction().commit();
		em.close();

	}


}
