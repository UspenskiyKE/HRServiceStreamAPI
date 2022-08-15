package com.flightofdream.EmployeeBookLibraries;

import java.util.HashMap;
public class EmployeeServiceImpl {
    private final HashMap<String,Employee> employeeMap=new HashMap<>();
    private final int listSize=5;

    public Employee addEmployee( String firstName, String lastName) {

        Employee e = new Employee(firstName, lastName);
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
        Employee e1=new Employee(firstName,lastName);
        Employee result=null;
        if (employeeMap.containsKey(e1.getFirstName()+e1.getLastName())) {
            result=e1;
        }else {
            throw new EmployeeNotFoundException("Employee Is Not Found!");
        }
        return result;
    }

    public HashMap<String,Employee> showEmployeeList(){
        return employeeMap;
    }
}