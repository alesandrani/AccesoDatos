package Main.persona;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import modelo.entidad.Coche;

public class MainJPAModificar {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPA");
		EntityManager em = emf.createEntityManager();
		
		System.out.println("EntityManager creado");
		
		Coche coche = new Coche();
		coche.setId(1);
		coche.setMarca("Toyota");
		coche.setModelo("Supra");
		coche.setKm(75000);
		
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.merge(coche);
		tx.commit();
		
		em.close();

	}

}
