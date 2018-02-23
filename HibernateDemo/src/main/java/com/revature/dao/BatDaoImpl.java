package com.revature.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;

import com.revature.domain.Animal;
import com.revature.domain.Bat;
import com.revature.util.HibernateUtil;

public class BatDaoImpl implements BatDao{

	
	@Override
	public List<Bat> getBats() {
		Session s = HibernateUtil.getSession();
		
		//SELECT everything FROM the Bat table
		//Bat refers to the name of the Java object that's mapped to a table in your database
		List<Bat> Bats = s.createQuery("from Bat").list();
		for (Bat b : Bats) {
			System.out.println(b);
		}
		s.close();
		return Bats;
	}

	@Override
	public Bat getBatById(int id) {
		Session s = HibernateUtil.getSession();
		Bat b = (Bat) s.get(Bat.class, id);
		
		s.close();
		return b;
	}

	@Override
	public void addBats(List<Bat> BatList) {
		Session s = HibernateUtil.getSession();
		
		//org.hibernate
		Transaction tx = s.beginTransaction();

		//We'll be using our save() method
		//save() returns the generated ID
		
		for(Bat b : BatList) {
			s.persist(b);
		}
		
		tx.commit();
		s.close();
	}
	
	public List<Animal> getBatsWithMediumWingspans(){
		
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		Criteria c = s.createCriteria(Animal.class);
		c.add(Restrictions.gt("wingspan", 50));
		c.add(Restrictions.lt("wingspan", 100));
		
		List<Animal> batList = c.list();
		
		tx.commit();
		s.close();
		
		return batList;
	}
	
	public Long getTotalWingSpan() {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		Criteria c = s.createCriteria(Bat.class);
		Projection p = Projections.property("wingspan");
		ProjectionList pList = Projections.projectionList();
		pList.add(p);
		
		c.add(Restrictions.isNotNull("wingspan"));
		
		c.setProjection(pList);
		
		List<Object> wingList = c.list();
		
		long total = 0;
		for (Object o : wingList) {
			total += Long.valueOf(o.toString());
		}
		
		System.out.println("Total Wingspan = "+total);
		
		tx.commit();
		s.close();
		
		return total;
	}
}
