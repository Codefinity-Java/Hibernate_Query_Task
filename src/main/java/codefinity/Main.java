package codefinity;

import codefinity.service.EmployeeService;
import codefinity.service.impl.EmployeeServiceImpl;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();

        System.out.println(employeeService.getEmployeesHiredInASpecificTimeframe("2024-01-01", "2025-01-01"));
        System.out.println(employeeService.getEmployeesNamesHiredInASpecificTimeframe("2021-01-01", "2022-01-01"));
    }
}
