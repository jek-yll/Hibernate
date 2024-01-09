package impl;

import dao.BaseDAO;
import model.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class ProductDao  implements BaseDAO<Product> {

    private static StandardServiceRegistry registry  = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory sessionFactory;

    public ProductDao(){
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    };

    @Override
    public boolean create(Product product) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();
            transaction.begin();

            session.save(product);
            return true;

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }  finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean update(Product productUpdated) {

        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();
            transaction.begin();

            session.update(productUpdated);
            return true;

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }  finally {
            session.close();
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();
            transaction.begin();

            Product p = session.load(Product.class, id);
            session.delete(p);

            return true;

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }  finally {
            session.close();
        }

        return false;
    }

    @Override
    public Product getById(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        Product p = null;

        try {
            transaction = session.getTransaction();
            transaction.begin();

            p = session.load(Product.class, id);
           // System.out.println(p);

        } catch (Exception e){
            if (transaction != null){
                transaction.rollback();
                e.printStackTrace();
            }
        }  finally {
            session.close();
        }
        return p;
    }

    public static void close(){
        sessionFactory.close();
    }
}
