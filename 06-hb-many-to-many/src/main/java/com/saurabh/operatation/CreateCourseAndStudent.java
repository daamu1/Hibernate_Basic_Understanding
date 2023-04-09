package com.saurabh.operatation;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.entity.Course;
import com.saurabh.entity.Instructor;
import com.saurabh.entity.InstructorDetail;
import com.saurabh.entity.Review;
import com.saurabh.entity.Student;

public class CreateCourseAndStudent {

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

			// Get a course
			Course tempCourse = session.get(Course.class, 12);
			// Add some Student
			Student student = new Student("Saurabh", "Maithani", "saurabhmaithani@123gmail.com");
			Student student2 = new Student("Vatsal", "Maithani", "vati@gmail.com");
			// add some reviews
			tempCourse.addStudent(student);
			tempCourse.addStudent(student2);
//			tempCourse.addReview(new Review("What a dumb course, you are an idiot!"));

			// save the course ... and leverage the cascade all :-)
			System.out.println("Saving the course");
			System.out.println(tempCourse);
			System.out.println(tempCourse.getStudents());
			
			session.save(student2);
			session.save(student);
			session.save(tempCourse);

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
