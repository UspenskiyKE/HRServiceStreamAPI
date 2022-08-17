package com.flightofdream.EmployeeBookLibraries;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

public class EmployeeServiceImpl {
    private static final HashMap<String,Employee> employeeMap=new HashMap<>();
    private static final int listSize=5;

    public static HashMap<String, Employee> getEmployeeMap() {
        return employeeMap;
    }

    public static int getListSize() {
        return listSize;
    }

    public static Employee addEmployee(String firstName, String lastName) {
        if(StringUtils.isAlpha(firstName)&&StringUtils.isAlpha(lastName)) {

            String lcFirstLetter = StringUtils.substring(firstName, 0, 1).toUpperCase();
            String firstLetter = StringUtils.substring(firstName, 0, 1);
            if (!firstLetter.equals(lcFirstLetter)) {
                firstName = StringUtils.capitalize(firstName);
            }

            lcFirstLetter = StringUtils.substring(lastName, 0, 1).toUpperCase();
            firstLetter = StringUtils.substring(lastName, 0, 1);
            if (!firstLetter.equals(lcFirstLetter)) {
                lastName = StringUtils.capitalize(lastName);
            }
        }else{
            throw new EmployeeFullNameIsNotAlpha("Employee Full Name Is Not Alpha!");
        }

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

    public static void showEmployeeList(){
         employeeMap.entrySet().forEach(e-> System.out.println(e.getValue().getFirstName()+" "+e.getValue().getLastName()));
    }
}
