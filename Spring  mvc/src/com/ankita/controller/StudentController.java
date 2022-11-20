package com.ankita.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ankita.entities.Student;
import com.ankita.services.StudentService;


public class StudentController {

	
	private StudentService studentService;


	
	public String listStudents(Model model) {
		List<Student> students = studentService.findAll();

		model.addAttribute("Students", students);

		return "list";
	}

	
	public String showFormForAdd(Model model) {
		Student student = new Student();
		model.addAttribute("Student", student);

		return "form";
	}

		public String showFormForUpdate(@RequestParam("studentId") int id, Model model) {
		Student student = studentService.findById(id);
		model.addAttribute("Student", student);

		return "form";
	}

	
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("dept") String dept, @RequestParam("country") String country) {
		
		System.out.println("The Id : " + id);
		Student student;
		if (id != 0) {
			student = studentService.findById(id);
			student.setName(name);
			student.setDept(dept);
			student.setCountry(country);
		}
		else {
			student = new Student(name, dept, country);
		}
		studentService.save(student);
		
		return "redirect:/participants/list";
	}
	
	
	public String deleteStudent(@RequestParam("studentId") int id) {
		studentService.deletById(id);
		
		return "redirect:/participants/list";
	}
	
}