package com.employee.EmployeeManagement.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employee.EmployeeManagement.Entity.Employee;
import com.employee.EmployeeManagement.Repository.EmployeeRepo;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmployeeService {
  
	@Autowired
	private  EmployeeRepo employeeRepo;
	
	public Employee addEmployee(Employee employee) {
		return employeeRepo.save(employee);
	}
	
	public List<Employee> getAllEmployees(){
		return employeeRepo.findAll();
	}
	
	public void deleteEmployee(Long id) {
		if(!employeeRepo.existsById(id)) {
			throw new EntityNotFoundException("Employee with Id "+id+" not Found");
		}
		employeeRepo.deleteById(id);
	}
	
	public Employee getElementById(Long id) {
		return employeeRepo.findById(id).orElse( null);
	}
	
	public Employee updateEmployeeById(Long id,Employee employee) {
		Optional<Employee> optional=employeeRepo.findById(id);
		if(optional.isPresent()) {
			Employee existingEmployee=optional.get();
			existingEmployee.setEmail(employee.getEmail());
			existingEmployee.setName(employee.getName());
			existingEmployee.setPhone(employee.getPhone());
			existingEmployee.setDepartment(employee.getDepartment());
			return employeeRepo.save(existingEmployee);
		}
		return null;
	}
}
