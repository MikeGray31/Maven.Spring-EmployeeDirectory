package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class DepartmentController {

    @Autowired
    private DepartmentServices departmentServices;


    @PostMapping(path = "/API/departments")
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        return new ResponseEntity<>(this.departmentServices.createDepartment(department), HttpStatus.CREATED);
    }

    @GetMapping(path = "/API/departments/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable("id") Long depId){
        return new ResponseEntity<>(this.departmentServices.getDepartment(depId), HttpStatus.OK);
    }

    @GetMapping(path = "/API/departments")
    public ResponseEntity<Iterable<Department>> getAllDepartments(){
        return new ResponseEntity<>(this.departmentServices.getAllDepartments(), HttpStatus.OK);
    }

    @PutMapping(path = "/API/departments/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long depId, @RequestBody Department newDep) {
        return new ResponseEntity<>(this.departmentServices.updateDepartment(depId, newDep), HttpStatus.OK);
    }

    @DeleteMapping(path = "/API/departments/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long depId){
        this.departmentServices.deleteDepartment(depId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/merge/{deptAId}-{deotBId}")
    public ResponseEntity<Department> mergeDepartments (@PathVariable Long deptAId, @PathVariable Long deptBId) {
        Department response = departmentServices.mergeDepartments(deptAId, deptBId);
        if (response != null) { return new ResponseEntity<Department>(response, HttpStatus.OK); }
        else { return new ResponseEntity<Department>(HttpStatus.BAD_REQUEST); }
    }
}
