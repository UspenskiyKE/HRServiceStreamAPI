package com.flightofdream.HRServiceStreamAPI;

import java.util.Objects;

public  class Employee {
    private String firstName;
    private String lastName;
    private String department;
    private double salary;

    public Employee(String firstName,  String lastName, String department, double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department=department;
        this.salary=salary;
    }



    @Override
    public String toString() {
        return firstName+"  " + lastName+" "+department+" "+salary;
    }

    @Override
    public boolean equals(Object other) {
        if (this.getClass() != other.getClass()) {
            return false;
        }
        Employee c2 = (Employee) other;
        return firstName.equals(c2.firstName)&&lastName.equals(c2.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, department, salary);
    }



    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setDepartment(String department) {
        this.department=department;
    }

    public void setSalary(double salary) {
        this.salary=salary;
    }
}


