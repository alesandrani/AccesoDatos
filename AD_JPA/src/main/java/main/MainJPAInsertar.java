package main;



import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import modelo.entidad.Persona;

public class MainJPAInsertar {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PruebaJPA");
		EntityManager em = emf.createEntityManager();
		System.out.println("entity manager creado");
		Persona p = new Persona();
		p.setNombre("Vegeta");
		p.setFechaNacimiento(new Date());
		p.setCasado(true);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		//no importa cuantas veces usemos persist porque siempre sera el mismo objeto
		//si quieremos dar de alta otro objeto, tedremos que crearlo
		p = new Persona();
		p.setNombre("Goku");
		em.persist(p);
		p= new Persona();
		p.setNombre("Bulma");
		Persona p2 = em.merge(p);//devuelve la referencia del objeto gestionado
		
		p.setNombre("Mutenroshi");
		//aqui no se cambia nada, por el objeto bulma es diferente al objeto bulma que esta gestionado
		tx.commit();
		em.close();
		

	}

}
