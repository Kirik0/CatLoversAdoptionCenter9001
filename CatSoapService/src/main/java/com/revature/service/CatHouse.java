package com.revature.service;

import java.util.List;

import javax.jws.WebService;

import com.revature.exception.TomCatException;
import com.revature.model.Cat;

@WebService
public interface CatHouse {
	//A contract (WSDL) will be generated based on this interface
	//Go to /Library?wsdl to see the file.
	
	public List<Cat> getAllCats();
	
	public String addCat(Cat cat) throws TomCatException;
}
