package demo;

import demo.model.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Demo {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        // session.getTransaction().begin();
        session.beginTransaction();

        Personne personne = new Personne();
        personne.setFirstname("Paul");
        personne.setLastname("Pierre");
        personne.setAge(10);

        session.save(personne);

        System.out.println("Personne : " + personne.getId());

        // Récupérer une personne :
//        Personne personne = session.load(Personne.class, 1L);
//        System.out.println("Personne : " + personne.getId());
//
//        // Modification d'une personne :
//        personne.setFirstname("Mathias");
//        session.update(personne);
//        System.out.println("Personne changé : " + personne);
//
//        // Delete personne :
//        session.delete(personne);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();

    }
}
