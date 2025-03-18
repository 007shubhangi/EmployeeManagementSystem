package com.employee.EmployeeManagement.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.EmployeeManagement.Entity.Employee;
import com.employee.EmployeeManagement.Repository.EmployeeRepo;
import com.employee.EmployeeManagement.Service.EmployeeService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin("*")
public class EmployeeController {
	
	@Autowired
	private  EmployeeService employeeService;
	
	@PostMapping("/employee")
	public Employee addEmployee(@RequestBody Employee employee) {
		return employeeService.addEmployee(employee);
	}
	
	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeService.getAllEmployees();
	}
	
	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id){
		try {
			employeeService.deleteEmployee(id);
			return new ResponseEntity<>("Employee with Id "+id+" Deleted Sucessfully!!",HttpStatus.OK);
		}catch (EntityNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		}
	}
	
	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getElementById(@PathVariable Long id){
		Employee employee=employeeService.getElementById(id);
		if(employee==null) {
			return ResponseEntity.notFound().build();
		}
		else {
			return ResponseEntity.ok(employee);
		}
	}
	
	@PatchMapping("/employee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,@RequestBody Employee employee) {
		Employee updatedEmployee=employeeService.updateEmployeeById(id, employee);
		if(updatedEmployee==null)
		{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		else {
			return ResponseEntity.ok(updatedEmployee);
		}
	}
	
}
