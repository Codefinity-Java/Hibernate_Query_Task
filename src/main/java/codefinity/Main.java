package codefinity;

import codefinity.service.DepartmentService;
import codefinity.service.EmployeeService;
import codefinity.service.impl.DepartmentServiceImpl;
import codefinity.service.impl.EmployeeServiceImpl;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeServiceImpl();
        DepartmentService departmentService = new DepartmentServiceImpl();

        System.out.println(employeeService.getAll());
        System.out.println(employeeService.getEmployeesWithSalaryMoreThan(50000.00));
        System.out.println(departmentService.getAll());

        //You can test your solution here
    }
}
