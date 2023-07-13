package org.skypro.CollectionFromListToMap.controller;

import org.skypro.CollectionFromListToMap.entity.Employee;
import org.skypro.CollectionFromListToMap.service.EmployeeService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler({HttpStatusCodeException.class})
    public String handlerException(Exception e) {
        return "Code: " + e.getMessage();
    }

    private final EmployeeService employeeService;


    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public String add(@RequestParam String firstName,
                        @RequestParam String lastName) {
        return employeeService.add(firstName, lastName);
    }

    @GetMapping("/get")
    public List<Employee> getAll() {
        return employeeService.getAll();
    }

    @GetMapping("/find")
    public String find(@RequestParam String firstName,
                         @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping("/remove")
    public String remove(@RequestParam String firstName,
                         @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);
    }
}
