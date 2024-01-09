package heritage;

import heritage.single_table.CreditCardPayment1;
import heritage.single_table.PayPalPayment1;
import heritage.table_per_class.CreditCardPayment2;
import heritage.table_per_class.PayPalPayment2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Date;

public class Main {
    public static void main(String[] args) {

        StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
        SessionFactory sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.getTransaction();
            tx.begin();
            // Exemple joined table :

    /*      CreditCardPayment creditCardPayment = new CreditCardPayment();
            creditCardPayment.setCardNumber("123456");
            creditCardPayment.setAmount(5000.0);
            creditCardPayment.setPaymentDate(new Date());
            creditCardPayment.setExpirationDate("12/2025");


            PayPalPayment payPalPayment = new PayPalPayment();
            payPalPayment.setAccountNumber("45879");
            payPalPayment.setPaymentDate(new Date());
            payPalPayment.setAmount(9854.5);

            session.save(creditCardPayment);
            session.save(payPalPayment);


            System.out.println("creditCardPayment " + creditCardPayment);
            System.out.println("payPalPayment " + payPalPayment);*/


            // Exemple signe table :

            /*CreditCardPayment1 creditCardPayment1 = new CreditCardPayment1();
            creditCardPayment1.setCardNumber("545487");
            creditCardPayment1.setPaymentDate(new Date());
            creditCardPayment1.setExpirationDate("02/2056");
            creditCardPayment1.setAmount(98214.25);

            PayPalPayment1 payPalPayment1 = new PayPalPayment1();
            payPalPayment1.setAccountNumber("8764640");
            payPalPayment1.setAmount(874597.254);
            payPalPayment1.setPaymentDate(new Date());

            session.save(payPalPayment1);
            session.save(creditCardPayment1);

            System.out.println("creditCardPayment " + creditCardPayment1);
            System.out.println("payPalPayment " + payPalPayment1);*/

            // Exemple table per class :

            CreditCardPayment2 creditCardPayment2 = new CreditCardPayment2();
            creditCardPayment2.setCardNumber("545487");
            creditCardPayment2.setPaymentDate(new Date());
            creditCardPayment2.setExpirationDate("02/2056");
            creditCardPayment2.setAmount(98214.25);

            PayPalPayment2 payPalPayment2 = new PayPalPayment2();
            payPalPayment2.setAccountNumber("8764640");
            payPalPayment2.setAmount(874597.254);
            payPalPayment2.setPaymentDate(new Date());

            session.save(payPalPayment2);
            session.save(creditCardPayment2);

            System.out.println("creditCardPayment " + creditCardPayment2);
            System.out.println("payPalPayment " + payPalPayment2);


            tx.commit();

        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
                ex.printStackTrace();
            }
        } finally {
            session.close();
            sessionFactory.close();
        }


    }
}
