package com.emp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.emp.entity.Employee;
import com.emp.service.EmployeeService;

import jakarta.servlet.http.HttpSession;




@Controller
public class EController {
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/") //first page
	public String home(Model m) {
		List<Employee> emp= service.getAllEmp();
		m.addAttribute("emp",emp);
		return "index";
	}
	
	@GetMapping("/addemp")
	public String addEmpForm(){
		return "addEmployee";
	}
	
	
	@PostMapping("/register")
	public String register(@ModelAttribute Employee e, HttpSession session) {
		System.out.println(e);
		
		service.addEmployee(e);
		session.setAttribute("message", "Employee added Sucessfully..");
		
		return "redirect:/";
	}
	
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable int id,Model m) {
		Employee e=service.getEmpById(id);
		m.addAttribute("emp",e);
		return "edit";
		
	}
	
	@PostMapping("/update")
	public String updateEmp(@ModelAttribute Employee e, HttpSession session)
	{
		service.addEmployee(e);
		session.setAttribute("message","Employee Data Updated successfully..");
		return "redirect:/";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteEmp(@PathVariable int id, HttpSession session) {
		service.deleteEmp(id);
		session.setAttribute("message","Employee Data Deleted successfully..");
		return "redirect:/";
		
	}
}
