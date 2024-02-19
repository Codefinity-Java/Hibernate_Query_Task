package codefinity.dao.impl;

import codefinity.dao.EmployeeDao;
import codefinity.model.Employee;
import codefinity.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.Date;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    private final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public Employee add(Employee employee) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(employee);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                throw new RuntimeException("Can't add new Employee", e);
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employee;
    }
    @Override
    public Employee getById(int id) {
        Session session = null;
        Employee employee = null;
        try {
            session = sessionFactory.openSession();
            employee = session.get(Employee.class, id);
        } catch (Exception e) {
            throw new HibernateException("Can't get Employee by ID " + id, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employee;
    }

    @Override
    public List<Employee> getEmployeesHiredInASpecificTimeframe(Date startDate, Date endDate) {
        Session session = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();

            String hql = "FROM Employee WHERE hireDate > :startDate AND hireDate <:endDate";

            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("startDate", startDate);
            query.setParameter("endDate", endDate);

            employees = query.getResultList();
        } catch (Exception e) {
            System.out.println("Can't get Employees from the DB" + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return employees;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }

    @Override
    public List<Employee> getEmployeesWithSalaryMoreThan(Double salary) {
        return null;
    }
}
