package com.saurabh.operatation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.entity.Course;
import com.saurabh.entity.Instructor;
import com.saurabh.entity.InstructorDetail;
import com.saurabh.entity.Review;
import com.saurabh.entity.Student;

public class DeleteStudent {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class)
				.addAnnotatedClass(Review.class).addAnnotatedClass(Student.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			int Id = 1;
			// create a Student
			Student student = session.get(Student.class, Id);

			// save the course ... and leverage the cascade all :-)
			System.out.println("Fetching the student");
			System.out.println(student);
			System.out.println(student.getCourses());
			session.delete(student);
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {

			// add clean up code
			session.close();

			factory.close();
		}
	}

}
