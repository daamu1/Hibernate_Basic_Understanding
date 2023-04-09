package com.saurabh.operatation;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.entity.Course;
import com.saurabh.entity.Instructor;
import com.saurabh.entity.InstructorDetail;
import com.saurabh.entity.Review;

public class CreateCourse {
	public static void main(String[] args) {

		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).addAnnotatedClass(Course.class).addAnnotatedClass(Review.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

//			Instructor tempInstructor = new Instructor("Pallavi ", "Maithani", "pallavi01921@gmail.com");
//			Instructor tempInstructor =session.get(Instructor.class, 4);
			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Singing");
		
			// create some courses
			Course tempCourse1 = new Course("Hibernate");
			Course tempCourse2 = new Course("Spring data Jpa");
			Course tempCourse3 = new Course("Spring Boot ");
			
//			tempInstructor.setInstructorDetail(tempInstructorDetail);
			session.beginTransaction();
			Instructor tempInstructor =session.get(Instructor.class, 1);
			System.out.println("Instructor :"+tempInstructor);
			// add courses to instructor
			tempInstructor.add(tempCourse1);
			tempInstructor.add(tempCourse2);
			tempInstructor.add(tempCourse3);

			// save the courses
			session.save(tempCourse1);
			session.save(tempCourse2);
			session.save(tempCourse3);
			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}
}
