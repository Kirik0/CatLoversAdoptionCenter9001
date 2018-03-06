package com.revature.model;

public class Cat {

	public Cat() {
		super();
	}
	
	public Cat(String name, String species, int yearBorn) {
		this();
		this.name = name;
		this.species = species;
		this.yearBorn = yearBorn;
	}
	
	
	private String name;
	private String species;
	private int yearBorn;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public int getYearBorn() {
		return yearBorn;
	}
	public void setYearBorn(int yearBorn) {
		this.yearBorn = yearBorn;
	}
	
	
	@Override
	public String toString() {
		return "Book [name=" + name + ", species=" + species + ", yearBorn=" + yearBorn + "]";
	}
	
}
