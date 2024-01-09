package demo;

import demo.model.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.hibernate.type.StringType;

import java.util.List;

public class Demo2 {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        String search = "P";

        // Recherche de personnes avec un paramètre + LIKE

        Query<Personne> personneQuery = session.createQuery("from Personne where firstname like :nom");
        personneQuery.setParameter("nom", search+"%", StringType.INSTANCE);
        List<Personne> personnes = personneQuery.list();

        for (Personne p : personnes){
            System.out.println("Personnes commencant par un P " + p);
        }


        Query<Personne> personneQuery1 = session.createQuery("from Personne where firstname like ?1");
        personneQuery1.setParameter(1, search+"%", StringType.INSTANCE);
        List<Personne> personnes1 = personneQuery.list();

        for (Personne p : personnes){
            System.out.println("Personnes commencant par un P " + p);
        }

    }
}
