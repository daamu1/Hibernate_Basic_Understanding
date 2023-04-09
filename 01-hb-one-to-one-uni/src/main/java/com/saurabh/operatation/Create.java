package com.saurabh.operatation;

import java.security.PublicKey;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.saurabh.entity.Instructor;
import com.saurabh.entity.InstructorDetail;

import antlr.collections.List;
import net.bytebuddy.dynamic.scaffold.MethodRegistry.Handler.ForAbstractMethod;

public class Create {
	public static void main(String[] args) {
		ArrayList<Instructor> instructors = new ArrayList<Instructor>();
		Instructor instr;
		
		// create session factory
		SessionFactory factory = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class).buildSessionFactory();

		// create session
		Session session = factory.getCurrentSession();

		try {

			// create the objects
			Instructor tempInstructor = new Instructor("Vishal", "Negi", "vick@gmail.com");

			InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.youtube.com", "Cricket");
			Instructor tempInstructor1 = new Instructor("Saurabh", "Negi", "shubh@gmail.com");

			InstructorDetail tempInstructorDetail1 = new InstructorDetail("http://www.youtube.com", "Cricket,Hockey");
			Instructor tempInstructor2 = new Instructor("Sakshi", "Negi", "aba@gmail.com");

			InstructorDetail tempInstructorDetail2 = new InstructorDetail("http://www.youtube.com", "Coding");
			instructors.add(tempInstructor);
			instructors.add(tempInstructor2);
			instructors.add(tempInstructor1);
		
			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();

			// save the instructor
			//
			// Note: this will ALSO save the details object
			// because of CascadeType.ALL
		
			System.out.println("Saving instructor: " + tempInstructor);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
