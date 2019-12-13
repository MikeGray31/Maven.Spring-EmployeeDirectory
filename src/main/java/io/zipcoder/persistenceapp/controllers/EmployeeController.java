package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
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

    @GetMapping(path = "API/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long empId) {
        return new ResponseEntity<>(this.service.findEmployeeById(empId), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/employeesByManager/{manId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesByManager(@PathVariable Long manId) {
        return new ResponseEntity<>(this.service.findEmployeesByManager(manId), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/employeeReportingHierarchy/{id}")
    public ResponseEntity<Iterable<Employee>> getReportingHierarchy(@PathVariable Long empId) {
        return new ResponseEntity<>(this.service.findReportingHierarchy(empId), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/directAndIndirectEmployees/{manId}")
    public ResponseEntity<Iterable<Employee>> getDirectAndIndirectEmployees(@PathVariable Long manId) {
        return new ResponseEntity<>(this.service.findDirectAndIndirectEmployees(manId), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/employeesByDepartment/noAssignedManager")
    public ResponseEntity<Iterable<Employee>> getEmployeesWithNoManager() {
        return new ResponseEntity<>(this.service.findEmployeesWithNoManager(), HttpStatus.OK);
    }

    @GetMapping(path = "API/employees/employeesByDepartment/{depId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesByDepartment(@PathVariable Long depId) {
        return new ResponseEntity<>(this.service.findEmployeeByDepartment(depId), HttpStatus.OK);
    }

    @PutMapping(path = "API/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long empId, @RequestBody Employee newEmp) {
        return new ResponseEntity<>(this.service.updateEmployee(empId, newEmp), HttpStatus.OK);
    }

    @DeleteMapping(path = "API/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long empId) {
        this.service.deleteEmployee(empId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "API/employees/{ids}")
    public ResponseEntity<?> deleteEmployees(@PathVariable("ids") Long... empIds) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "API/employees/employeesByDepartment/{depId}")
    public ResponseEntity<?> deleteEmployeesInDepartment(@PathVariable Long depId) {
        this.service.deleteEmployeeByDepartment(depId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



//    public void verifyEmployee(Long empId) {
//        if(!this.service.verifyEmployee(empId)) {
//            throw new ResourceNotFoundException();
//        }
//    }
}
