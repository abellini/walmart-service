package com.walmart.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.facade.PopulateDistanceFacade;
import com.walmart.model.Distance;

@RestController
@RequestMapping("/ws-distance")
public class PopulateDistanceController {

	@Autowired
	PopulateDistanceFacade distanceFacade;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<String> create(@Valid @RequestBody Distance distances, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("JSON Validation Error", HttpStatus.BAD_REQUEST);
		}

		if (distanceFacade.create(distances)) {
			return new ResponseEntity<String>("Distances populated", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error populated distances", HttpStatus.BAD_REQUEST);
		}

	}
}
