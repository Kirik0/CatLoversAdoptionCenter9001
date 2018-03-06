package com.revature.service;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

import com.revature.exception.TomCatException;
import com.revature.model.Cat;

//URL pattern uses /*, but we haven't decided which endpoint we'll be exposing
//to the world yet.
@WebService(endpointInterface="com.revature.service.CatHouse")
public class CatHouseImpl implements CatHouse {

	@Override
	public List<Cat> getAllCats() {
		List<Cat> catList = new ArrayList<>();
		
		catList.add(new Cat("Sylvester", "cattus", 1960));
		catList.add(new Cat("Garfield", "Orange Tabby", 1961));
		catList.add(new Cat("Crissie", "Brown Tabby", 2009));
		
		return catList;
	}

	@Override
	public String addCat(Cat cat) throws TomCatException {
		//Not really doing anything in particular
		if(cat.getName().equals("Tom")) {
			throw new TomCatException();
		}
		return cat.getName() + " has arrived!";
	}
	
}
