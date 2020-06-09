package infraestructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import domain.Ferramenta;
import domain.Gaveta;
import domain.Operacao;
import util.JPAUtil;

public class OperacaoDBDAO implements OperacaoDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<Operacao> mostraOperacoes() {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT op FROM Operacional op";
		Query query = em.createQuery(jpql);
		List<Operacao> operacoes = query.getResultList();
		return operacoes;
	}

	@Override
	public void add(Operacao operacao) {
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		operacao = em.merge(operacao);
		em.persist(operacao);
		em.getTransaction().commit();
		em.close();

	}

	@Override
	public String buscaGaveta(Ferramenta fSearch) {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT g.nome FROM Operacional o inner join o.gaveta g WHERE o.ferramenta.id = :idFerramenta group by g.id, g.nome having sum(case when type(o) = 'Compra' then o.quantidade else -o.quantidade end) > 0";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("idFerramenta", fSearch.getId());
		List<String> result = query.getResultList();
		if (result.size() > 0) {
			return result.get(0);
		}
		return null;
	}

	@Override
	public List<Operacao> buscarOperacoes(Ferramenta ferramenta, Gaveta gaveta) {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT o FROM Operacional o WHERE o.ferramenta.id = :idFerramenta AND o.gaveta.id = :idGaveta";
		TypedQuery<Operacao> query = em.createQuery(jpql, Operacao.class);
		query.setParameter("idFerramenta", ferramenta.getId());
		query.setParameter("idGaveta", gaveta.getId());
		List<Operacao> result = query.getResultList();

		return result;
	}

	@Override
	public List<Operacao> buscarOperacoes(Ferramenta ferramenta) {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT o FROM Operacional o WHERE o.ferramenta.id = :idFerramenta";
		TypedQuery<Operacao> query = em.createQuery(jpql, Operacao.class);
		query.setParameter("idFerramenta", ferramenta.getId());
		List<Operacao> result = query.getResultList();

		return result;
	}

	@Override
	public List<Operacao> buscarOperacoes(Gaveta gaveta) {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT o FROM Operacional o WHERE o.gaveta.id = :idGaveta";
		TypedQuery<Operacao> query = em.createQuery(jpql, Operacao.class);
		query.setParameter("idGaveta", gaveta.getId());
		List<Operacao> result = query.getResultList();

		return result;
	}

	@Override
	public List<String> buscaFerramentas(Gaveta gaveta) {
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT f.nome FROM Operacional o inner join o.ferramenta f WHERE o.gaveta.id = :idGaveta group by f.id, f.nome having sum(case when type(o) = 'Compra' then o.quantidade else -o.quantidade end) > 0";
		TypedQuery<String> query = em.createQuery(jpql, String.class);
		query.setParameter("idGaveta", gaveta.getId());
		List<String> result = query.getResultList();

		return result;
	}

	@Override
	public long getQtdGaveta(Ferramenta ferramenta, Gaveta gaveta) {
//		if (gaveta == null || ferramenta == null ) return 0l;
		EntityManager em = new JPAUtil().getEntityManager();
		String jpql = "SELECT SUM(case when type(o) = 'Compra' THEN o.quantidade ELSE -o.quantidade END) FROM Operacional o WHERE o.ferramenta.id = :idFerramenta AND o.gaveta.id = :idGaveta";
		TypedQuery<Long> query = em.createQuery(jpql, Long.class);
		query.setParameter("idFerramenta", ferramenta.getId());
		query.setParameter("idGaveta", gaveta.getId());
		Long result = query.getSingleResult();
		return result == null ? 0l : result;
	}
}
