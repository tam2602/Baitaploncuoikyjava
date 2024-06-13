package test;

import java.math.BigDecimal;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import model.UserAccount;
import util.HibernateUtil;

public class TestHibernate {
    public static void main(String[] args) {
        try {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
            if (sessionFactory != null) {
                Session session = sessionFactory.openSession();
                try {
                    Transaction tr = session.beginTransaction();
                    UserAccount account = new UserAccount();
                    account.setAccountNumber("12345");
                    BigDecimal balance = new BigDecimal("22.121455");
                    account.setBalance(balance);
                    account.setFullName("Minh Tam");
                    account.setUsername("minhtam123");
                    account.setRole("admin");
                    account.setPassword("12345678");
                    session.save(account);
                    tr.commit();
                } finally {
                    session.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
