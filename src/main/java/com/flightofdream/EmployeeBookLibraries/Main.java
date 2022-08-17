package com.flightofdream.EmployeeBookLibraries;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String firstName;
        String lastName;
        try(Scanner  sc=new Scanner(System.in)){

            while (EmployeeServiceImpl.getEmployeeMap().size()<EmployeeServiceImpl.getListSize())
            { System.out.println("Имя сотрудника:");
            firstName=sc.nextLine();
            System.out.println("Фамилия сотрудника:");
            lastName=sc.nextLine();
            Employee e=EmployeeServiceImpl.addEmployee(firstName,lastName);}
            EmployeeServiceImpl.showEmployeeList();
        }
    }
}