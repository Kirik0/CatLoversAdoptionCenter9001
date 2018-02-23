package com.revature.driver;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.dao.*;
import com.revature.domain.Animal;
import com.revature.domain.Bat;
import com.revature.domain.Bear;
import com.revature.domain.Category;
import com.revature.domain.Cave;
import com.revature.domain.Flashcard;
import com.revature.util.HibernateUtil;


public class Driver {

	
	public static void main(String[] args) {
		//init();
		
		//FlashcardDao fd = new FlashcardDaoImpl();
		//If we comment out the transaction and commit statements, this will still work
		
		//Save is less controllable, and it may or may not actually save your changes
		//if it is not in a transaction.
		//saveAndPersist(fd);
		
		//updateAndMerge(fd);
		
		//Category c1 = new Category(3, "Coding");
		//funWithNamedQueries(c1);
		
		//funWithCache();
		
		//caveInit();
		
		//animalInit();
		
		//moreAnimals();
		
		BatDaoImpl bdi = new BatDaoImpl();
		List<Animal> batList = bdi.getBatsWithMediumWingspans();
		
		for(Animal b : batList) {
			System.out.println(b.toString());
		}
	}
		
	static void getVsLoad() {
		
		//Get vs Load
		FlashcardDao fd = new FlashcardDaoImpl();
		Flashcard f3 = fd.getFlashcardById(3);
		
		if(f3 != null) {
			System.out.println("Flashcard 3 exists!");
			System.out.println(f3);
			//If Category is lazily fetched, the line below throws an exception
			//System.out.println(f3.getCategory());
		} else {
			System.out.println("Flashcard 3 does not exist. :(");
		}
	}
	
	static void saveAndPersist(FlashcardDao fd) {
		System.out.println(fd.addFlashcard(new Flashcard("What version of Java do we use?", "Java 8", new Category(1, ""))));
	}
	
	static void updateAndMerge(FlashcardDao fd) {
		Flashcard f1 = new Flashcard("Where should you stand if you're cold?",
						"In the corner, because it's 90 degrees", new Category(2, "")); //Transient object
		Session s1 = HibernateUtil.getSession();
		Transaction tx1 = s1.beginTransaction();
		
		int genId = (int) s1.save(f1);
		
		tx1.commit();
		s1.close();
		
		//f1 is detached. It was part of a persistence context (when it was saved),
		//but is no longer (since the session closed).
		Session s2 = HibernateUtil.getSession();
		Transaction tx2 = s2.beginTransaction();
		try {
			Flashcard f2 = (Flashcard) s2.get(Flashcard.class, genId);
			//f2 is persistent and has the same persistence identity (same id) as the detached object f1
			
			f1.setAnswer("The corner, it's 90 degrees.");
			//f1 is still detached. We haven't called a method that brings it from
			//transient to persistent.
			
			//s2.update(f1);
			//In this case, we'll get a NonUniqueObjectException if we use update()
			//Made a duplicate object and tried to update the original one.
			//Update attempts to reattach our object to s2. Doesn't check whether anything in s2
			//already has that identifier.
			//If you comment out f2's instantiation, you can call s2.update(f1);
			
			s2.merge(f1);	//This is fine
			
			tx2.commit();
		} catch (Exception e) {
			e.printStackTrace();
			tx2.rollback();
		}
		s2.close();
	}
	
	static void init() {
		
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();		//import org.hibernate.Transaction;
		
		//FlashcardDao fd = new FlashcardDaoImpl();
		//fd.getFlashcards();
		
		//Create some categories
		Category c1 = new Category("Coding");
		Category c2 = new Category("Jokes");
		
		//Casting because we can potentially get something that isn't an int as a Primary Key
		//int newCategory1 = (int) s.save(c1);
		//int newCategory2 = (int) s.save(c2);
		
		//Create some flashcards to save
		//Category inserted first by Hibernate, since Flashcard depends on Category
		Flashcard f1 = new Flashcard("What is Java?", "The coolest language.", c1/*new Category(newCategory1, "Coding")*/);
		Flashcard f2 = new Flashcard("Where are the bears?", "Hibernating.", c2/*new Category(newCategory1, "Coding")*/);
		Flashcard f3 = new Flashcard("What did the DBA tell his inebriated friend?", "You had one-to-many", c2);
		
		//fd.addFlashcard(f1);
		//fd.addFlashcard(f2);
		
		s.save(f1);
		s.save(f2);
		s.saveOrUpdate(f3);
		
		tx.commit();
		s.close();		
	}

	static void funWithNamedQueries(Category c) {
		
		//Trying to find Flashcards by Category in some kind of list
		Session s = HibernateUtil.getSession();
		Query q = s.getNamedQuery("findCardByCategory");	//org.hibernate
		q.setInteger("categoryVar",  c.getId());
		
		List<Flashcard> cards = q.list();	//It's annoyed because we aren't casting
		System.out.println(cards.size());
		
		Iterator<Flashcard> itr = cards.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		
		s.close();
		//Note: no transaction because nothing was changed
	}
	
	static void funWithCriteria() {
		Session s = HibernateUtil.getSession();
		//We'll add Restrictions with .light
		//We'll do Criterion with Bears, SO STAY TUNED
		
		
		
		s.close();
	}
	
	static void funWithCache() {
		Session s = HibernateUtil.getSession();
		
		Flashcard f = (Flashcard) s.load(Flashcard.class, 7);
		System.out.println(f.getQuestion());
		//We're within a session, so we're fine here
		
		s.evict(f);
		
		//prints the Java object we made, not checking the cache.
		System.out.println(f.getQuestion());
		
		//Does not work, since f was removed from the cache
		System.out.println(s.contains(f));
		
		s.close();
	}

	static void caveInit() {
		Cave c1 = new Cave("Reston");
		Cave c2 = new Cave("Herndon");
		
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		//Don't need primary keys right away
		//Just wanna throw these caves in
		s.persist(c1);
		s.persist(c2);
		
		tx.commit();
		s.close();
	}

	//This should be happening via DAO methods
	static void animalInit() {
		
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		//Grab our persistent cave objects
		//If we try to find a Cave that does not exist, c will be set to null.
		//When there is a possibility of getting a null value, handle it!
		Cave c = (Cave)s.get(Cave.class, 2);
		
		Animal a = new Animal("Mystery", c);
		Bat bat1 = new Bat("Ernie", c, 46);
		Bear bear1 = new Bear("Fred", c, "Grizzly");
		
		s.save(a);
		s.save(bat1);
		s.save(bear1);
		
		tx.commit();
		s.close();
	}

	static void moreAnimals() {
		Session s = HibernateUtil.getSession();
		Transaction tx = s.beginTransaction();
		
		//Grab our persistent cave objects
		//If we try to find a Cave that does not exist, c will be set to null.
		//When there is a possibility of getting a null value, handle it!

		Cave c = new Cave(1, "Reston");
		
		
		Bat bat1 = new Bat("Frodo", c, 12);
		Bat bat2 = new Bat("Sam", c, 70);
		Bat bat3 = new Bat("Aragorn", c, 140);
		Bear bear1 = new Bear("Gandalf", c, "Wizard");
		Bear bear2 = new Bear("Fred2", c, "Wizard");
		Bear bear3 = new Bear("Saruman", c, "Polar");
		
		List<Bear> bearList = new ArrayList<>();
		
		bearList.add(bear1);
		bearList.add(bear2);
		bearList.add(bear3);

		List<Bat> batList = new ArrayList<>();
		
		batList.add(bat1);
		batList.add(bat2);
		batList.add(bat3);
		
		BearDao bearDao = new BearDaoImpl();
		BatDao batDao = new BatDaoImpl();
		
		bearDao.addBears(bearList);
		batDao.addBats(batList);
		
		s.save(bat1);
		s.save(bat2);
		s.save(bat3);
		s.save(bear1);
		s.save(bear2);
		s.save(bear3);
		
		tx.commit();
		s.close();
	}
	
	
	
}
