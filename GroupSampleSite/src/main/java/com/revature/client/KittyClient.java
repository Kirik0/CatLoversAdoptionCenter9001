package com.revature.client;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.revature.beans.Cat;

@Component(value="kittyClient")
public class KittyClient {
	
	private String resourceUrl;
	
	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}
	
	@Autowired
	private RestTemplate restTemplate;
	
	public ArrayList<Cat> getKitties(){
		ResponseEntity<? extends ArrayList<Cat>> response = this.restTemplate.postForEntity(this.resourceUrl+"/cat/all", null, (Class<? extends ArrayList<Cat>>)ArrayList.class);
		return response.getBody();
	}
}
