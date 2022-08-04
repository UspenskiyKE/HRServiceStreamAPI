package com.flightofdream.HRServiceStreamAPI;
import java.util.ArrayList;

public interface EmployeeService {
    Employee addEmployee( String firstName, String lastName);
    Employee removeEmployee( String firstName, String lastName);
    Employee findEmployee(String firstName, String lastName);
    double monthSalaryExpenses();
    double departmentSalaryExpenses(String departmentName);
    Employee minSalaryEmployee();
    Employee departmentMinSalaryEmployee(String departmentName);
    Employee maxSalaryEmployee();
    Employee departmentMaxSalaryEmployee(String departmentName);
    ArrayList<Employee> departmentEmployees(String departmentName);
    void showEmployeeList();
    void showEmployeesForDepartments();
}


