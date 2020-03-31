package edu.matc.persistence;

import edu.matc.entity.*
        ;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class UserDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public UserDao() {}

    /**
     * Get user by id
     */
    public User getById(int id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        logger.debug("User id # " + id + ": " + user);
        session.close();
        return user;
    }

    /**
     * Update user
     * @param user  user to be updated
     */
    public void saveOrUpdate(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(user);
        transaction.commit();
        logger.debug("Updated User: " + user);
        session.close();
    }

    /**
     * Insert user
     * @param user  user to be inserted
     */
    public int insert(User user) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(user);
        transaction.commit();
        logger.debug("User Inserted: " + user);
        session.close();
        return id;
    }

    /**
     * Delete an user
     * @param user user to be deleted
     */
    public void delete(User user) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        logger.debug("Deleted User: " + user);
        transaction.commit();
        session.close();
    }

    /**
     * Return a list of all users
     * @return All users
     */
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<User> query = builder.createQuery(User.class);
        Root<User> root = query.from(User.class);
        List<User> users = session.createQuery(query).getResultList();
        logger.debug("List of all users: " + users);
        session.close();
        return users;
    }
}
