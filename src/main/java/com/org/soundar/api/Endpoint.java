package com.org.soundar.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.soundar.dto.InputDto;
import com.org.soundar.exception.AppCustomException;
import com.org.soundar.service.ListingService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/")
@Slf4j
public class Endpoint {

	@Autowired
	ListingService service;

	@PostMapping(value = "properties", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postFolderProperties(@RequestBody InputDto input) {
		log.info("Inside Endpoint.postFolderProperties()");

		String response = service.folderPropService(input);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping(value = "filelist", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> postFileList(@RequestBody InputDto input) throws AppCustomException {
		log.info("Inside Endpoint.postFileList()");

		String response = service.fileListService(input);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping(value = "duplicate-list", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findDuplicates(@RequestBody InputDto input) throws AppCustomException {
		log.info("Inside Endpoint.findDuplicates()");

		String response = service.findDuplicateService(input);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping(value = "search", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findSearched(@RequestBody InputDto input) throws AppCustomException {
		log.info("Inside Endpoint.findSearched()");

		String response = "";

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping(value = "size-zero", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findSizeZero(@RequestBody InputDto input) throws AppCustomException {
		log.info("Inside Endpoint.findSizeZero()");

		String response = service.findSizeZeroService(input);

		return new ResponseEntity<>(response, HttpStatus.OK);

	}

}