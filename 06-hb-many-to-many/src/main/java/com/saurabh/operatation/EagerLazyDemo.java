package com.saurabh.operatation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.entity.Course;
import com.saurabh.entity.Instructor;
import com.saurabh.entity.InstructorDetail;

public class EagerLazyDemo {

	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// start a transaction
			session.beginTransaction();

			// get the instructor from db
			int theId = 1;
			Instructor tempInstructor = session.get(Instructor.class, theId);

			System.out.println("Saurabh:Instructor: " + tempInstructor);
			// alternative way to get Lazy loading
			System.out.println("Saurabh:Courses: " + tempInstructor.getCourses());

			// commit transaction
			session.getTransaction().commit();

			// for testing lazy loading Let's close the session and then see
			session.close();
			// get courses for the instructor
			System.out.println("Saurabh:Courses: " + tempInstructor.getCourses());

			System.out.println("Done!");
		} finally {

			// add clean up code
			session.close();

			factory.close();
		}
	}

}
