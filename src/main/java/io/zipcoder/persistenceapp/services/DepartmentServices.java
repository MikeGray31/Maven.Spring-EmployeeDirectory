package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.models.Employee;
import io.zipcoder.persistenceapp.repositories.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServices {

    @Autowired
    DepartmentRepo departmentRepo;

    @Autowired
    EmployeeServices employeeServices;

    public Department createDepartment(Department department) {
        return this.departmentRepo.save(department);
    }

    public Department getDepartment(Long id) {
        return this.departmentRepo.findOne(id);
    }

    public Iterable<Department> getAllDepartments() {
        return this.departmentRepo.findAll();
    }

    public Department updateDepartment(Long id, Department newDepartment) {
        Department original = this.departmentRepo.findOne(id);
        original.setDepartmentManager(newDepartment.getDepartmentManager());
        original.setDepartmentName(newDepartment.getDepartmentName());
        return this.departmentRepo.save(original);
    }

    public void deleteDepartment(Long id){
        this.departmentRepo.delete(id);
    }

    public Department mergeDepartments(Long depAId, Long depBId) {
        Department departmentA = getDepartment(depAId);
        Department departmentB = getDepartment(depBId);
        if (departmentA != null && departmentB != null) {
            departmentB.getDepartmentManager().setDepartmentKey(depAId); // move mgr
            for (Employee e : employeeServices.findEmployeeByDepartment(depBId)) {
                e.setDepartmentKey(depAId); // move the others
            }
            departmentRepo.delete(departmentB);
            return departmentA;
        } else {
            return null;
        }


    }
}
