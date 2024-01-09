package demo;

import demo.model.Personne;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class Demo3 {

    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // FONCTION D'AGREGATION
        // MAX()
        Query<Integer> query = session.createQuery("select max(age) from Personne");
        int maxAge = query.uniqueResult();
        System.out.println("Age max : " + maxAge);

        //AVG()
        double moyenneAge =(double) session.createQuery("select avg(age) from Personne ").uniqueResult();
        System.out.println("Moyenne age : " + moyenneAge);

        // UTILISATION DU IN
        List noms = new ArrayList<String>();

        noms.add("Toto");
        noms.add("Mimi");
        Query<Personne> query1 = session.createQuery("from Personne where lastname in :noms");
        query1.setParameter("noms", noms);

        List<Personne> personneList = query1.list();

        for (Personne p : personneList) {
            System.out.println("Personne :" + p);
        }

        // Utilisation execute update
        String updateQuery = "update Personne set lastname = :nomP where id=2";
        Query query2 = session.createQuery(updateQuery);
        query2.setParameter("nomP", "Titi");
        int success = query2.executeUpdate();

        session.getTransaction().commit();
        System.out.println(success);
        session.close();
    }
}
