package codefinity.dao.impl;

import codefinity.dao.DepartmentDao;
import codefinity.model.Department;
import codefinity.model.Employee;
import codefinity.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DepartmentDaoImpl implements DepartmentDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Department add(Department department) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(department);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("Can't add new Department", e);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return department;
    }

    @Override
    public Department getById(int id) {
        Session session = null;
        Department department = null;
        try {
            session = sessionFactory.openSession();
            department = session.get(Department.class, id);
        } catch (Exception e) {
            throw new HibernateException("Can't get Department by ID " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return department;
    }
}
