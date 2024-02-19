package codefinity.service;

import codefinity.model.Department;
import codefinity.service.impl.DepartmentServiceImpl;
import codefinity.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

class DepartmentServiceTest {
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    DepartmentService departmentService = new DepartmentServiceImpl();

    @Test
    void getAll() {
        List<Department> expected = getAllDepartments();
        List<Department> actual = departmentService.getAll();
        assertEquals(expected.size(), actual.size(), "Some elements are missing. Double-check the correctness of the method's operation");
        assertEquals(expected, actual, "The list elements do not match the expected ones.");
    }

    private List<Department> getAllDepartments() {
        Session session = null;
        List<Department> departments = null;

        try {
            session = sessionFactory.openSession();

            String hql = "FROM Department";

            Query<Department> query = session.createQuery(hql, Department.class);

            departments = query.getResultList();

        } catch (Exception e) {
            System.out.println("Can't get departments" + e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return departments;
    }
}