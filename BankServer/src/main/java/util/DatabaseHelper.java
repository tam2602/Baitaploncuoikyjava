package util;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import model.UserAccount;

import java.math.BigDecimal;
import java.util.List;

public class DatabaseHelper {

    public synchronized static boolean authenticateUser(String username, String password) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<UserAccount> query = session.createQuery("FROM UserAccount WHERE username = :username AND password = :password", UserAccount.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            UserAccount user = query.uniqueResult();
            return user != null;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public synchronized static String getUserRole(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        String role = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM UserAccount WHERE username = :username AND password = :password";
            Query<UserAccount> query = session.createQuery(hql, UserAccount.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            UserAccount user = query.uniqueResult();
            if (user != null) {
                role = user.getRole();
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return role;
    }

    public synchronized static boolean registerUser(UserAccount user) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    public synchronized static boolean deleteUser(String accountNumber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserAccount user = session.get(UserAccount.class, accountNumber);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public synchronized static boolean updateUser(UserAccount userAccount) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.update(userAccount);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public synchronized static boolean napTien(String accountNumber, BigDecimal sotien) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserAccount user = session.get(UserAccount.class, accountNumber);
            if (user != null) {
                user.setBalance(user.getBalance().add(sotien));
                session.update(user);
                transaction.commit();
                return true;
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public synchronized static boolean rutTien(String accountNumber, BigDecimal sotien) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserAccount user = session.get(UserAccount.class, accountNumber);
            if (user != null) {
                if (user.getBalance().compareTo(sotien) >= 0) {
                    user.setBalance(user.getBalance().subtract(sotien));
                    session.update(user);
                    transaction.commit();
                    return true;
                } else {
                    throw new Exception("Insufficient funds");
                }
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public synchronized static boolean chuyenTien(String fromAccount, String toAccount, BigDecimal sotien) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            UserAccount fromUser = session.get(UserAccount.class, fromAccount);
            UserAccount toUser = session.get(UserAccount.class, toAccount);
            if (fromUser != null && toUser != null) {
                if (fromUser.getBalance().compareTo(sotien) >= 0) {
                    fromUser.setBalance(fromUser.getBalance().subtract(sotien));
                    toUser.setBalance(toUser.getBalance().add(sotien));
                    session.update(fromUser);
                    session.update(toUser);
                    transaction.commit();
                    return true;
                } else {
                    throw new Exception("Insufficient funds");
                }
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    public synchronized static UserAccount findUser(String accountNumber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return session.get(UserAccount.class, accountNumber);
        } finally {
            session.close();
        }
    } 
    public static List<UserAccount> getAllUserAccounts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<UserAccount> userAccounts = null;
        try {
            transaction = session.beginTransaction();
            userAccounts = session.createQuery("FROM UserAccount", UserAccount.class).list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return userAccounts;
    }
    
    public synchronized static UserAccount getUserAccount(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        UserAccount user = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM UserAccount WHERE username = :username AND password = :password";
            Query<UserAccount> query = session.createQuery(hql, UserAccount.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            user = query.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return user;
    }
    public synchronized static List<UserAccount> searchUsers(String keyword) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<UserAccount> users = null;
        try {
            transaction = session.beginTransaction();
            String hql = "FROM UserAccount WHERE username LIKE :keyword OR fullName LIKE :keyword";
            Query<UserAccount> query = session.createQuery(hql, UserAccount.class);
            query.setParameter("keyword", "%" + keyword + "%");
            users = query.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return users;
    }
}
