package codefinity.service;

import codefinity.model.Employee;
import codefinity.service.impl.EmployeeServiceImpl;
import codefinity.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

class EmployeeServiceTest {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private final EmployeeService employeeService = new EmployeeServiceImpl();

    @Test
    void getEmployeesWithSalaryMoreThan() {
        Double salary = 50000.00;

        List<Employee> expectedEmployees = getEmployeesWithSalaryMoreThan(salary);
        List<Employee> result = employeeService.getEmployeesWithSalaryMoreThan(salary);
        assertEquals(expectedEmployees.size(), result.size(), "Some elements are missing. Double-check the correctness of the method's operation");
        assertEquals(expectedEmployees, result, "The list elements do not match the expected ones.");
    }

    @Test
    void getAllEmployees() {
        List<Employee> expectedEmployees = getAll();

        List<Employee> result = employeeService.getAll();

        assertEquals(expectedEmployees.size(), result.size(), "Some elements are missing. Double-check the correctness of the method's operation");
        assertEquals(expectedEmployees, result, "The list elements do not match the expected ones.");

    }

    private List<Employee> getAll() {
        Session session = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();

            String hql = "FROM Employee";

            Query<Employee> query = session.createQuery(hql, Employee.class);
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

    private List<Employee> getEmployeesWithSalaryMoreThan(Double salary) {
        Session session = null;
        List<Employee> employees = null;
        try {
            session = sessionFactory.openSession();

            String hql = "FROM Employee WHERE salary > :salary";

            Query<Employee> query = session.createQuery(hql, Employee.class);
            query.setParameter("salary", salary);
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
}
