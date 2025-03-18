package com.employee.EmployeeManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.employee.EmployeeManagement.Entity.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long>{

}
