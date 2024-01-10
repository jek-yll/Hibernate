package impl;

import dao.BaseDAO;
import model.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class OrderDao implements BaseDAO<Order> {

    private static final StandardServiceRegistry registry  = new StandardServiceRegistryBuilder().configure().build();
    private static SessionFactory sessionFactory;

    public OrderDao(){
        sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
    }

    @Override
    public boolean create(Order element) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.getTransaction();
            transaction.begin();

            session.save(element);
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
    public boolean update(Order element) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Order getById(Long id) {
        return null;
    }

    @Override
    public List<Order> getALL() {
        return null;
    }
}
