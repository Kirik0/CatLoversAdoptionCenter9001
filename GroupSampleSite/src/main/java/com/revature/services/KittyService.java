package com.revature.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.revature.beans.Cat;
import com.revature.dao.CatDao;
import com.revature.dao.CatDaoImpl;

@Service("kittyService")
public class KittyService {
	
	CatDao cd = new CatDaoImpl();
	
	public List<Cat> getAllCats(){
		List<Cat> cats = cd.getCats();
		return cats;
	}
}