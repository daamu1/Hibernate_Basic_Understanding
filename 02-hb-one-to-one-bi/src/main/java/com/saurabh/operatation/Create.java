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

			// associate the objects
			tempInstructor.setInstructorDetail(tempInstructorDetail);

			// start a transaction
			session.beginTransaction();
			tempInstructorDetail.setInstructor(tempInstructor);
			// save the instructor
			//
			// Note: this will ALSO save the details objec
			// because of CascadeType.ALL
			session.save(tempInstructorDetail);
			System.out.println("Saving instructor: " + tempInstructorDetail);
			session.save(tempInstructor);

			// commit transaction
			session.getTransaction().commit();

			System.out.println("Done!");
		} finally {
			factory.close();
		}
	}

}
