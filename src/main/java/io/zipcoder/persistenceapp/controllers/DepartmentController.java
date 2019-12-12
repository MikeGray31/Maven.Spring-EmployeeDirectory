package io.zipcoder.persistenceapp.controllers;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.services.DepartmentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class DepartmentController {

    private DepartmentServices departmentServices;

    @Autowired
    public DepartmentController(@Autowired DepartmentServices departmentServices) {
        this.departmentServices = departmentServices;
    }


    @PostMapping(path = "/API/departments")
    public ResponseEntity<?> createDepartment(@RequestBody Department department){
        return null;
    }

    @GetMapping(path = "API/departments/{id}")
    public ResponseEntity<?> getDepartment(@PathVariable("id") Long depId){
        return null;
    }

    @GetMapping(path = "API/departments")
    public ResponseEntity<Iterable<Department>> getAllDepartments(){
        return null;
    }

    @PutMapping(path = "API/departments/{id}")
    public ResponseEntity<?> updateDepartment(@PathVariable("id") Long depId, @RequestBody Department newDep){
        return null;
    }

    @DeleteMapping(path = "API/departments/{id}")
    public ResponseEntity<?> deleteDepartment(@PathVariable("id") Long depId){
        return null;
    }
}
