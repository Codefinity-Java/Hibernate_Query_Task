package codefinity.service.impl;

import codefinity.dao.EmployeeDao;
import codefinity.dao.impl.EmployeeDaoImpl;
import codefinity.model.Employee;
import codefinity.service.EmployeeService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.regex.Pattern;

public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao = new EmployeeDaoImpl();

    @Override
    public Employee add(Employee employee) {
        return employeeDao.add(employee);
    }

    @Override
    public Employee getById(int id) {
        Employee employee = employeeDao.getById(id);
        if (employee != null) {
            return employeeDao.getById(id);
        } else {
            throw new NoSuchElementException("Can't get employee by ID " + id);
        }
    }

    @Override
    public String getEmployeeNameById(int id) {
        Employee employee = getById(id);
        String employeeName = employee.getName();
        if (employeeName != null) {
            return employeeName;
        } else {
            throw new NullPointerException("The employee's name is null, " +
                    "or there is no name for an employee with ID " + id);
        }
    }

    @Override
    public List<Employee> getEmployeesHiredInASpecificTimeframe(String startDate, String endDate) {
        Date from = dateParser(startDate);
        Date to = dateParser(endDate);
        return employeeDao.getEmployeesHiredInASpecificTimeframe(from, to);
    }

    @Override
    public List<String> getEmployeesNamesHiredInASpecificTimeframe(String startDate, String endDate) {
        List<Employee> employees = getEmployeesHiredInASpecificTimeframe(startDate, endDate);
        List<String> names = employees.stream().map(Employee::getName).toList();
        return names;
    }

    private Date dateParser(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        Date result = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        return result;
    }
}
