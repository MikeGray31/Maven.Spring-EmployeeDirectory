package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    private EmployeeServices service;

    @Autowired
    public EmployeeController(EmployeeServices employeeServices) {
        this.service = employeeServices;
    }

    @PostMapping(path = "/API/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(this.service.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping(path = "API/employees")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        return new ResponseEntity<>(this.service.findAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/employeesByDep/{depId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesByDepartment(@PathVariable Long depId) {
        return new ResponseEntity<>(this.service.findEmployeeByDepartment(depId), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/employeesByManager/{manId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesByManager(@PathVariable Long manId) {
        return new ResponseEntity<>(this.service.findEmployeeByManager(manId), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long empId){
        return null;
    }

    @GetMapping(path = "API/employees/{managerId}/{id}")
    public ResponseEntity<Iterable<Employee>> getEmployeesUnderManager(@PathVariable("managerId") Long manId, @PathVariable("id") Long empId) {
        return null;
    }

    @GetMapping(path = "API/departments/{depId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesInDepartment(){
        return null;
    }

    @PutMapping(path = "API/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long empId, @RequestBody Employee newEmp){
        return null;
    }

    @DeleteMapping(path = "API/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long empId){
        return null;
    }
}
