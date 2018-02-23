package com.revature.dao;

import java.util.List;

import com.revature.domain.Animal;
import com.revature.domain.Bat;

public interface BatDao {
	public List<Bat> getBats();
	
	public Bat getBatById(int id);
	
	public void addBats(List<Bat> batList);
	
	public List<Animal> getBatsWithMediumWingspans();
}
