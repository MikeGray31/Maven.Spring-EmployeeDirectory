package io.zipcoder.persistenceapp.services;

import io.zipcoder.persistenceapp.models.Department;
import io.zipcoder.persistenceapp.repositories.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServices {

    private DepartmentRepo departmentRepo;

    @Autowired
    public DepartmentServices(DepartmentRepo departmentRepo) {
        this.departmentRepo = departmentRepo;
    }

    public Department createDepartment(Department department) {
        return this.departmentRepo.save(department);
    }

    public Department getDepartment(Long id) {
        return this.departmentRepo.findOne(id);
    }

    public Department updateDepartment(Department newDepartment, Long id) {
        Department original = this.departmentRepo.findOne(id);
        original.setDepartmentManager(newDepartment.getDepartmentManager());
        original.setDepartmentName(newDepartment.getDepartmentName());
        return this.departmentRepo.save(original);
    }

    public Boolean deleteDepartment(Long id){
        this.departmentRepo.delete(id);
        return true;
    }
}
