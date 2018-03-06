package com.revature.main;

import javax.xml.bind.annotation.*;

import com.revature.service.Cat;

@XmlRootElement
public class CatTests {
	Cat jimmy;
	
	@XmlElement(name="cat")
	public Cat getCatData() {
		return jimmy;
	}
	
	public void setCatData(Cat jimmy) {
		this.jimmy = jimmy;
	}
}
