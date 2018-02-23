package com.revature.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;

import com.revature.domain.Bear;
import com.revature.util.HibernateUtil;

public class BearDaoImpl implements BearDao {

	@Override
	public List<Bear> getBears() {
		Session s = HibernateUtil.getSession();
		
		//SELECT everything FROM the BEAR table
		//Bear refers to the name of the Java object that's mapped to a table in your database
		List<Bear> bears = s.createQuery("from Bear").list();
		for (Bear b : bears) {
			System.out.println(b);
		}
		s.close();
		return bears;
	}

	@Override
	public Bear getBearById(int id) {
		Session s = HibernateUtil.getSession();
		Bear b = (Bear) s.get(Bear.class, id);
		
		s.close();
		return b;
	}

	@Override
	public void addBears(List<Bear> bearList) {
		Session s = HibernateUtil.getSession();
		
		//org.hibernate
		Transaction tx = s.beginTransaction();

		//We'll be using our save() method
		//save() returns the generated ID
		
		for(Bear b : bearList) {
			s.persist(b);
		}
		
		tx.commit();
		s.close();
	}
	
	public long countBears()

    {

        Session s = HibernateUtil.getSession();

        Transaction tx = null;

        List rows = null;

        Number n = null;

        try

        {

            tx = s.beginTransaction();

            Criteria c = s.createCriteria(Bear.class);

            c.setProjection(Projections.rowCount()).getClass();

            rows = c.list();

            // Get the count of the rows;

            //System.out.println("Total Bears: "+rows.get(0));

            n = (Number) rows.get(0);

            tx.commit();

            

        }

        catch(HibernateException e)

        {

            if(tx != null)

                tx.rollback();

            e.printStackTrace();

		}

		finally {

			s.close();

		}

		return n.longValue();

    }
}
