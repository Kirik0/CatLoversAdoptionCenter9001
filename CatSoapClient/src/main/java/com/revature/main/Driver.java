package com.revature.main;

import java.io.File;
import java.net.URL;
import java.util.Arrays;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import com.revature.service.AddCat;
import com.revature.service.Cat;
import com.revature.service.CatHouse;
import com.revature.service.CatHouseImplService;

public class Driver {

	 public static void main(String[] args) throws Exception {
       /*JAXBContext jc = JAXBContext.newInstance(CatTests.class);
       Unmarshaller unmarshaller = jc.createUnmarshaller();
       File xml = new File("src/main/resources/input.xml");
       CatTests tests = (CatTests) unmarshaller.unmarshal(xml);
       Marshaller marshaller = jc.createMarshaller();
       marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
       marshaller.marshal(tests, System.out);*/
		 
		URL wsdlUrl = new URL("http://ec2-54-165-93-229.compute-1.amazonaws.com:8080/CatSoapService/CatHouse?wsdl");
		//In the wsdl
		//First argument: targetNamespace/xmlns:tns
		//Second argument: name
		QName qname = new QName("http://service.revature.com/", "CatHouseImplService");
		
		
		Service service = Service.create(wsdlUrl, qname);
		
		//Use the interface
		CatHouse catService = service.getPort(CatHouse.class);
		
		Cat jimmy = new Cat();
		jimmy.setName("Jimmy");
		jimmy.setSpecies("Persian");
		jimmy.setYearBorn(2015);
		
		System.out.println("Order completed: " + catService.addCat(jimmy));
		
		for(Cat c : catService.getAllCats()) {
			System.out.println(c.getName() + ", "+c.getSpecies() + ", " + c.getYearBorn());
		}
   }
}
