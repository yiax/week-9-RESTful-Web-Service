package edu.matc.persistence;

import edu.matc.entity.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class RoleDao {
    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    public RoleDao() {}

    /**
     * Get role by id
     */
    public Role getById(int id) {
        Session session = sessionFactory.openSession();
        Role role = session.get(Role.class, id);
        logger.debug("Role id # " + id + ": " + role);
        session.close();
        return role;
    }

    /**
     * Update role
     * @param role  role to be updated
     */
    public void saveOrUpdate(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(role);
        transaction.commit();
        logger.debug("Updated Role: " + role);
        session.close();
    }

    /**
     * Insert role
     * @param role  role to be inserted
     */
    public int insert(Role role) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int) session.save(role);
        transaction.commit();
        logger.debug("Role Inserted: " + role);
        session.close();
        return id;
    }

    /**
     * Delete an role
     * @param role role to be deleted
     */
    public void delete(Role role) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(role);
        logger.debug("Deleted Role: " + role);
        transaction.commit();
        session.close();
    }

    /**
     * Return a list of all Role
     * @return All roles
     */
    public List<Role> getAllRole() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Role> query = builder.createQuery(Role.class);
        Root<Role> root = query.from(Role.class);
        List<Role> roles = session.createQuery(query).getResultList();
        logger.debug("List of all roles: " + roles);
        session.close();
        return roles;
    }
}
