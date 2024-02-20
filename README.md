# Hibernate_Query_Task

In the previous chapter, we implemented 2 methods in the `Employee` service layer. We used **queries** to fetch the necessary data, and now you will have to do a similar task.

Your work will involve 4 classes:
1. `DepartmentDaoImpl`;
2. `EmployeeDaoImpl`;
3. `DepartmentServiceImpl`;
4. `EmployeeServiceImpl`.

You need to implement methods such as `getAll` and `getEmployeesWithSalaryMoreThan(Double salary)`. To implement these methods, you will need to use either `Query` or `NativeQuery`, depending on what you choose to use, **HQL** or **SQL**. You will also need to use parameter substitution and return a list.

After you implement the methods, you can **test your solution** in the `Main` class, and then **run the tests** that I have written for you to check if you have implemented everything correctly.

>Note
>
>Don't forget to set up a connection to your database in `hibernate.cfg.xml`.