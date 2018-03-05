package com.revature.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.revature.beans.Cat;
import com.revature.services.KittyService;


@Controller("kittyController")
@RequestMapping("/cats")
public class KittyController {
	
	@Autowired
	KittyService kittyService;
	
	@PostMapping("/all")
	@ResponseBody
	public ResponseEntity<List<Cat>> getAllKitties() {
		return new ResponseEntity<>(kittyService.getAllCats(), HttpStatus.OK);
	}
}
