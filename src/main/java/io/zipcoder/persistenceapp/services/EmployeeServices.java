package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeServices {

    //fields

    private EmployeeRepo employeeRepo;

    //constructor

    @Autowired
    public EmployeeServices(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }

    // methods

    public Employee createEmployee(Employee employee){
        return employeeRepo.save(employee);
    }

    public Iterable<Employee> findAllEmployees() {
        return employeeRepo.findAll();
    }

    public Employee findEmployeeById(Long id){
        return employeeRepo.findOne(id);
    }

    public Iterable<Employee> findEmployeeByDepartment(long depId) {
        ArrayList<Employee> result = new ArrayList<>();
        for(Employee e : findAllEmployees()){
            if(e.getDepartmentKey() == depId) result.add(e);
        }
        return result;
    }

    public ArrayList<Employee> findEmployeesByManager(Long manId) {
        ArrayList<Employee> result = new ArrayList<>();
        for(Employee e : findAllEmployees()){
            if(e.getManager().getEmployeeNumber() == manId) result.add(e);
        }
        return result;
    }

    public Iterable<Employee> findEmployeesWithNoManager() {
        ArrayList<Employee> result = new ArrayList<>();
        for(Employee e : findAllEmployees()){
            if(e.getManager().getEmployeeNumber() == null) result.add(e);
        }
        return result;
    }

    public Iterable<Employee> findReportingHierarchy(Long empId) {
        Employee e = this.employeeRepo.findOne(empId);
        ArrayList<Employee> result = new ArrayList<>();
        result.add(e);
        Boolean reachedTopManager = false;

        while(!reachedTopManager){
            if(e.getManager() == null){
                reachedTopManager = true;
            }
            else {
                e = e.getManager();
                result.add(e);
            }
        }
        return result;
    }

    public ArrayList<Employee> findDirectAndIndirectEmployees(Long manId) {
        ArrayList<Employee> results = findEmployeesByManager(manId);
        for(Employee e : results) {
            if(findEmployeesByManager(e.getEmployeeNumber()) != null) {
                results.addAll(findDirectAndIndirectEmployees(e.getEmployeeNumber()));
            }
        }
        return results;
    }

    public Employee updateEmployee(Long empId, Employee newEmp) {
        Employee original = employeeRepo.findOne(empId);
        original.setDepartmentKey(newEmp.getDepartmentKey());
        original.setEmail(newEmp.getEmail());
        original.setFirstName(newEmp.getFirstName());
        original.setLastName(newEmp.getLastName());
        original.setHireDate(newEmp.getHireDate());
        original.setManager(newEmp.getManager());
        original.setPhoneNumber(newEmp.getPhoneNumber());
        original.setTitle(newEmp.getTitle());
        return this.employeeRepo.save(original);
    }

    public void deleteEmployee(Long empId) {
        ArrayList<Employee> underlings = findEmployeesByManager(empId);
        for (Employee e : underlings) {
            if (findEmployeeById(empId).getManager() != null) {
                e.setManager(findEmployeeById(empId).getManager());
                updateEmployee(e.getEmployeeNumber(), e);
            }
        }
        this.employeeRepo.delete(empId);
    }

    public void deleteEmployeeByDepartment(Long depId) {
        for(Employee e : findEmployeeByDepartment(depId)){
            deleteEmployee(e.getEmployeeNumber());
        }
    }

    public void deleteDirectAndIndirectReports(Long manId){
        for(Employee e : findDirectAndIndirectEmployees(manId)){
            deleteEmployee(e.getEmployeeNumber());
        }
    }

    public void deleteDirectReports(Long manId){
        for(Employee e : findEmployeesByManager(manId)){
            deleteEmployee(e.getEmployeeNumber());
        }
    }

    public void mergeDepartments(Long depIdA, Long depIdB){

    }



//    public Boolean verifyEmployee(Long id) throws ResourceNotFoundException {
//        return this.employeeRepo.exists(id);
//    }
}
