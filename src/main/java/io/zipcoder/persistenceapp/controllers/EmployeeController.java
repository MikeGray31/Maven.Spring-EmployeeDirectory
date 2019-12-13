package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    @PostMapping(path = "/API/employees")
    public ResponseEntity<?> createEmployee(@RequestBody Employee employee) {
        return new ResponseEntity<>(this.employeeServices.createEmployee(employee), HttpStatus.CREATED);
    }

    @GetMapping(path = "/API/employees")
    public ResponseEntity<Iterable<Employee>> getAllEmployees() {
        return new ResponseEntity<>(this.employeeServices.findAllEmployees(), HttpStatus.OK);
    }

    @GetMapping(path = "/API/employees/{id}")
    public ResponseEntity<?> getEmployee(@PathVariable("id") Long empId) {
        return new ResponseEntity<>(this.employeeServices.findEmployeeById(empId), HttpStatus.OK);
    }

    @GetMapping(path = "/API/employees/ByManager/{manId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesByManager(@PathVariable Long manId) {
        return new ResponseEntity<>(this.employeeServices.findEmployeesByManager(manId), HttpStatus.OK);
    }

    @GetMapping(path = "/API/employees/ReportingHierarchy/{id}")
    public ResponseEntity<Iterable<Employee>> getReportingHierarchy(@PathVariable Long empId) {
        return new ResponseEntity<>(this.employeeServices.findReportingHierarchy(empId), HttpStatus.OK);
    }

    @GetMapping(path = "/API/employees/directAndIndirectReports/{manId}")
    public ResponseEntity<Iterable<Employee>> getDirectAndIndirectEmployees(@PathVariable Long manId) {
        return new ResponseEntity<>(this.employeeServices.findDirectAndIndirectEmployees(manId), HttpStatus.OK);
    }

    @GetMapping(path = "/API/employees/ByDepartment/noAssignedManager")
    public ResponseEntity<Iterable<Employee>> getEmployeesWithNoManager() {
        return new ResponseEntity<>(this.employeeServices.findEmployeesWithNoManager(), HttpStatus.OK);
    }

    @GetMapping(path = "/API/employees/ByDepartment/{depId}")
    public ResponseEntity<Iterable<Employee>> getEmployeesByDepartment(@PathVariable Long depId) {
        return new ResponseEntity<>(this.employeeServices.findEmployeeByDepartment(depId), HttpStatus.OK);
    }

    @PutMapping(path = "/API/employees/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable("id") Long empId, @RequestBody Employee newEmp) {
        return new ResponseEntity<>(this.employeeServices.updateEmployee(empId, newEmp), HttpStatus.OK);
    }

    @DeleteMapping(path = "/API/employees/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long empId) {
        this.employeeServices.deleteEmployee(empId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/API/employees/{ids}")
    public ResponseEntity<?> deleteEmployees(@PathVariable("ids") Long[] empIds) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = "/API/employees/employeesByDepartment/{depId}")
    public ResponseEntity<?> deleteEmployeesInDepartment(@PathVariable Long depId) {
        this.employeeServices.deleteEmployeeByDepartment(depId);
        return new ResponseEntity<>(HttpStatus.OK);
    }



//    public void verifyEmployee(Long empId) {
//        if(!this.employeeServices.verifyEmployee(empId)) {
//            throw new ResourceNotFoundException();
//        }
//    }
}
