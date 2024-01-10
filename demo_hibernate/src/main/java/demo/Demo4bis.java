package demo;

import demo.composeKey.Employee;
import demo.embedded.Agence;
import demo.embedded.AgenceId;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Demo4bis {

    public static void main(String[] args) {
        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        AgenceId agenceId = new AgenceId(12345, "Lille-Agence");

        Agence agence = new Agence(agenceId, "10 rue des arbres 59000 New York");

        session.save(agence);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
    }

}
