package com.org.soundar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.soundar.dto.InputDto;
import com.org.soundar.service.ListingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/")
@Slf4j
public class Endpoint {
	
	@Autowired
	ListingService service;

	@PostMapping("properties")
	public ResponseEntity<String> postFolderProperties(@RequestBody InputDto input) {
		log.info("Inside Endpoint.postFolderProperties()");
		
		
		return new ResponseEntity<>(input.toString(), HttpStatus.OK);
		
	}
	
}
