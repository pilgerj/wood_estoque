package teste;

import domain.Ferramenta;
import domain.Gaveta;
import domain.OperacaoRepository;
import infraestructure.OperacaoDAO;
import infraestructure.OperacaoDBDAO;
import infraestructure.OperacaoRepositoryImplementation;

public class TesteUnitarioDB {

	public static void main(String[] args) {
		Gaveta g1 = new Gaveta(1, "gavetaUm");
		Gaveta g2 = new Gaveta(2, "gavetaDois");
		Gaveta g3 = new Gaveta(3, "gaveta3");
		Gaveta g4 = new Gaveta(4, "gaveta4");
		
		Ferramenta f1 = new Ferramenta("Martelo", 1);
		Ferramenta f2 = new Ferramenta("Prego", 2);
		Ferramenta f3 = new Ferramenta("Janderson", 3);
		Ferramenta f4 = new Ferramenta("Estenio", 4);

//		EntityManager em = new JPAUtil().getEntityManager();
//
//		em.getTransaction().begin();
//
//		em.persist(f2);
//		em.persist(f1);
//		em.persist(f3);
//		em.persist(f4);
//		em.persist(g3);
//		em.persist(g4);
//		
//		em.getTransaction().commit();
//		em.close();

		//FerramentaDBDAO dbFerramenta = new FerramentaDBDAO();
//		dbFerramenta.removeFerramenta(f4);
		
//		GavetaDBDAO dbGaveta= new GavetaDBDAO();
//		dbGaveta.removeGaveta(g2);
				
//		Compra c5 = new Compra(new Date(), f3, g3, 20);
//		Venda v2 = new Venda(new Date(), f2, g3, 17);
		OperacaoDAO operacaoDAO = new OperacaoDBDAO();
		OperacaoRepositoryImplementation operacaoRepositoryImplementation = new OperacaoRepositoryImplementation(operacaoDAO);
		OperacaoRepository operacaoRepository = operacaoRepositoryImplementation;
		
//		dbOp.add(c5);
//		dbOp.add(v2);
		
//		System.out.println(dbGaveta.getGavetas().toString());
		
//		System.out.println(dbGaveta.buscarOperacoes(g4));
		
//		dbOp.buscaFerramentas(g3).forEach(System.out::println);
		operacaoRepository.buscaGaveta(f2);
//		dbOp.mostraOperacoes().forEach(System.out::println);
//		dbOp.buscarOperacoes(f2, g3).forEach(System.out::println);
//		dbOp.buscarOperacoes(f2).forEach(System.out::println);
//		dbOp.buscarOperacoes(g3).forEach(System.out::println);
//		System.out.println(dbOp.getQtdGaveta(f3, g3));
		
	}
	
	
	
} 