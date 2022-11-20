package com.ankita.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.ankita.entities.Student;


public interface StudentService {
	
	public List<Student> findAll();
	
	public Student	findById(int id);
	
	public void save(Student student);
	
	public void deletById(int id);
	
	//public List<Student> searchBy(String name, String dept, String country);
	
}