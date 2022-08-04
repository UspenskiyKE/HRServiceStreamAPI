package com.flightofdream.HRServiceStreamAPI;

import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.lang.Double;


@Service
public abstract class EmployeeServiceImpl implements EmployeeService{

    private final HashMap<String,Employee> employeeMap=new HashMap<>();
    private final int listSize=5;



    public  Employee addEmployee( String firstName, String lastName, String department, double salary) {

        Employee e = new Employee(firstName, lastName, department, salary);
        if(!employeeMap.containsKey(e.getFirstName()+e.getLastName())) {

            if(employeeMap.size()<listSize) {

                employeeMap.put(e.getFirstName()+e.getLastName(),e);
            }else {
                throw new EmployeeStorageIsFullException("Employee Storage Is Full!");
            }

        }else {
            throw new EmployeeAlreadyAddedException("Employee Is Already Added!");

        }
        return e;
    }

    public Employee removeEmployee( String firstName, String lastName) {
        Employee f=findEmployee(firstName,lastName);
        if(f!=null) {
            employeeMap.remove(f.getFirstName()+f.getLastName());
        }else{
            throw new EmployeeNotFoundException("Employee Is Not Found!");
        }
        return f;
    }

    public Employee findEmployee(String firstName, String lastName) {
        Employee e1=new Employee(firstName,lastName,"",0.0);
        Employee result=null;
        if (employeeMap.containsKey(e1.getFirstName()+e1.getLastName())) {
            result=e1;
        }else {
            throw new EmployeeNotFoundException("Employee Is Not Found!");
        }
        return result;
    }

    //Методы, добавленные из курсовой
    public  double monthSalaryExpenses() {
        //Считает суммарные затраты на зарплату для всех сотрудников
        AtomicReference<Double> theSum = new AtomicReference<>(0.0);
        employeeMap.forEach((key, value)->{
           theSum.accumulateAndGet(value.getSalary(),(x,y)->x+y);
                });
        String s=theSum.toString();
        double sum=Double.parseDouble(s);
        return sum;
    }

    public  double departmentSalaryExpenses(String departmentName) {
        //Считает суммарные затраты на зарплату для сотрудников данного отдела
        AtomicReference<Double> theSum = new AtomicReference<>(0.0);
        ArrayList<Employee> departmentEmployees=(ArrayList<Employee>) employeeMap.values()
                .stream()
                .filter(e->e.getDepartment().equals(departmentName))
                .collect(Collectors.toList());

        departmentEmployees.forEach(e->{
            theSum.accumulateAndGet(e.getSalary(),(x,y)->x+y);
        });
        String s=theSum.toString();
        double sum=Double.parseDouble(s);
        return sum;
    }

    public  Employee minSalaryEmployee() {
        //Находит сотрудника с минимальной зарплатой среди всех сотрудников
        Comparator<Employee> salaryComparator = new EmployeeSalaryComparator();
        ArrayList<Employee> sortedEmployees= (ArrayList<Employee>) employeeMap.values()
                .stream()
                .sorted(salaryComparator)
                .collect(Collectors.toList());
        return sortedEmployees.get(0);
    }

    public  Employee departmentMinSalaryEmployee(String departmentName) {
        //Находит сотрудника с минимальной зарплатой среди сотрудников данного отдела
        Comparator<Employee> salaryComparator = new EmployeeSalaryComparator();

        ArrayList<Employee> departmentEmployees= (ArrayList<Employee>) employeeMap.values()
                .stream()
                .filter(e->e.getDepartment().equals(departmentName))
                .sorted(salaryComparator)
                .collect(Collectors.toList());
        return departmentEmployees.get(0);
    }


    public  Employee maxSalaryEmployee() {
        //Находит сотрудника с максимальной зарплатой среди всех сотрудников
        Comparator<Employee> salaryComparator = new EmployeeSalaryComparator();
        ArrayList<Employee> sortedEmployees= (ArrayList<Employee>) employeeMap.values()
                .stream()
                .sorted(salaryComparator)
                .collect(Collectors.toList());
        return sortedEmployees.get(sortedEmployees.size()-1);
    }

    public  Employee departmentMaxSalaryEmployee(String departmentName) {
        //Находит сотрудника с максимальной зарплатой среди всех сотрудников данного отдела
        Comparator<Employee> salaryComparator = new EmployeeSalaryComparator();

        ArrayList<Employee> departmentEmployees= (ArrayList<Employee>) employeeMap.values()
                .stream()
                .filter(e->e.getDepartment().equals(departmentName))
                .sorted(salaryComparator)
                .collect(Collectors.toList());
        return departmentEmployees.get(departmentEmployees.size()-1);
    }

    public ArrayList<Employee> departmentEmployees(String departmentName) {
        ArrayList<Employee> departmentEmployees= (ArrayList<Employee>) employeeMap.values()
                .stream()
                .filter(e->e.getDepartment().equals(departmentName))
                .collect(Collectors.toList());
        return departmentEmployees;
    }

    public void showEmployeeList() {
        //Выводит список всех сотрудников
        System.out.println("Имя:        Фамилия:        Отдел:       Зарплата:");
        employeeMap.entrySet().forEach(e->{
            System.out.println(e);
        });
    }

    public void showEmployeesForDepartments() {
        //Выводит список сотрудников по отделам
        System.out.println("Имя:        Фамилия:        Отдел:       Зарплата:");
        ArrayList<String> departments=new ArrayList<>();
         employeeMap.values().forEach(e->{
            departments.add(e.getDepartment());
        });
        departments.forEach(d->{
          departmentEmployees(d).forEach(e->{
              System.out.println(e);
          });
        });
    }
}