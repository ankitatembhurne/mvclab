package com.ankita.services;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatlearning.entities.Student;


public class StudentServiceImpl implements StudentService {
	
	private SessionFactory sessionFactory;
	private Session session;
	

	public StudentServiceImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();
		} catch(HibernateException ex) {
			session = sessionFactory.openSession();
		}
	}

	public List<Student> findAll() {
	
		Transaction tx = session.beginTransaction();
		List<Student> students = session.createQuery("from Student").list();
		tx.commit();
		
		return students;
	}

	
	public Student findById(int id) {
		
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		tx.commit();
		
		return student;
	}

	
	public void save(Student student) {
		
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(student);
		tx.commit();
	}


	public void deletById(int id) {
	
		Transaction tx = session.beginTransaction();
		Student student = session.get(Student.class, id);
		if (student != null) {
			session.delete(student);
		}
		tx.commit();
	}
	
}