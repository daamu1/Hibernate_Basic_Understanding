package com.saurabh.operatation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.entity.Instructor;
import com.saurabh.entity.InstructorDetail;

public class DeleteInstructor {
	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();
		// create session
		Session session = factory.getCurrentSession();
		try {
			// start a transaction
			session.beginTransaction();
			// Get Instructer from instructor
			int id = 2;
			InstructorDetail instructor = session.get(InstructorDetail.class, id);
			if (instructor == null) {
				System.out.println("this is null value can't be deleted");
			}
			session.delete(instructor);
			// commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}
}