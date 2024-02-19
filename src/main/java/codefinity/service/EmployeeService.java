package codefinity.service;

import codefinity.model.Employee;

import java.util.List;

public interface EmployeeService {
    Employee add(Employee employee);

    Employee getById(int id);

    String getEmployeeNameById(int id);

    List<Employee> getEmployeesHiredInASpecificTimeframe(String startDate, String endDate);

    List<String> getEmployeesNamesHiredInASpecificTimeframe(String startDate, String endDate);

    List<Employee> getAll();

    List<Employee> getEmployeesWithSalaryMoreThan(Double salary);
}
