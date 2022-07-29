package com.flightofdream.HRService;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;

    public Employee(String firstName,  String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return this.firstName+"  " + this.lastName;
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
        return Objects.hash(firstName, lastName);
    }



    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }
}


