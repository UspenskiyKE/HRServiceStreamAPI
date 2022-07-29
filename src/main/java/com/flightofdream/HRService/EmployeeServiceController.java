package com.flightofdream.HRService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RequestMapping("/employee")
@RestController
public class EmployeeServiceController {
    private final EmployeeService employeeService;

    public EmployeeServiceController(EmployeeService employeeService) {

        this.employeeService=employeeService;
    }

    @GetMapping("/add")
    public Employee addEmployee(@RequestParam("fname") String firstName, @RequestParam("lname") String lastName) {
        Employee d=null;
        d=employeeService.addEmployee(firstName, lastName);

        return d;

    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam("fname") String firstName, @RequestParam("lname") String lastName){
        Employee d=null;
        d=employeeService.removeEmployee(firstName,lastName);

        return d;
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam("fname") String firstName, @RequestParam("lname") String lastName) {
        Employee d=null;
        d=employeeService.findEmployee(firstName,lastName);

        return d;
    }
    @GetMapping("/list")
    public HashMap<String,Employee> showEmployeeList(){
        return employeeService.showEmployeeList();
    }
}