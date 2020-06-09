package util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {

	private static EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("db");

	public EntityManager getEntityManager() {
		return entityManagerFactory.createEntityManager();
	}

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("db");
	EntityManager entityManager = factory.createEntityManager();

}