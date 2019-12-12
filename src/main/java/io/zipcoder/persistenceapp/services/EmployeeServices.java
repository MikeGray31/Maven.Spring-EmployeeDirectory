package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeServices {

    private EmployeeRepo employeeRepo;

    @Autowired
    public EmployeeServices(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    public Employee createEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public Iterable<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Iterable<Employee> findEmployeeByDepartment(long depId) {
        Iterable<Employee> allEmployees = findAllEmployees();
        ArrayList<Employee> result = new ArrayList<>();
        for(Employee e : allEmployees){
            if(e.getManager().getDepartmentKey() == depId){ result.add(e); }
        }
        return result;
    }

    public Iterable<Employee> findEmployeeByManager(Long manId) {
        Iterable<Employee> allEmployees = findAllEmployees();
        ArrayList<Employee> result = new ArrayList<>();
        for(Employee e : allEmployees){
            if(e.getManager().getManager().getEmployeeNumber() == manId){ result.add(e); }
        }
        return result;
    }
}
