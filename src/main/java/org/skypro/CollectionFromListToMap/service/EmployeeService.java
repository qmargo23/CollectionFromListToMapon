package org.skypro.CollectionFromListToMap.service;

import org.skypro.CollectionFromListToMap.entity.Employee;
import org.skypro.CollectionFromListToMap.exception.EmployeeAlreadyAddedException;
import org.skypro.CollectionFromListToMap.exception.EmployeeNotFoundException;
import org.skypro.CollectionFromListToMap.exception.EmployeeStorageIsFullException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();
    private final Map<String, Employee> employeeMap = new HashMap<>();

    private final static int MAX_SIZE = 4;

    public String add(String firstName, String lastName) {
        if (employees.size() >= MAX_SIZE) {
            throw new EmployeeStorageIsFullException("переполнение массива сотрудников");
        }
        String key = firstName + lastName;

        Employee newEmployee = new Employee(firstName, lastName);

        employeeMap.put(key, newEmployee);

        if (employees.contains(newEmployee)) {
            throw new EmployeeAlreadyAddedException("такой сотрудник уже есть");
        }
        employees.add(newEmployee);
        return newEmployee + "___ДОБАВЛЕН";
    }

    public String find(String firstName, String lastName) {
        Employee forFind = new Employee(firstName, lastName);
        for (Employee employee : employees) {
            if (employee.equals(forFind)) {
                return forFind + "___НАЙДЕН";
            }
        }
        throw new EmployeeNotFoundException("сотрудник не найден");
    }

    public String remove(String firstName, String lastName) {
        Employee forRemove = new Employee(firstName, lastName);
        boolean removeResult = employees.remove(forRemove);
        if (removeResult) {
            return forRemove + "___УДАЛЕН";
        } else {
            throw new EmployeeNotFoundException("удалить невозможно - сотрудник не найден");
        }
    }

    public List<Employee> getAll() {
        return employees;
    }
}
