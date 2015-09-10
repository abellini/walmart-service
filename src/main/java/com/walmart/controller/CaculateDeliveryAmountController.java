package com.walmart.controller;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.walmart.facade.DeliveryFacade;
import com.walmart.model.Delivery;

@RestController
@RequestMapping("/ws-amount")
public class CaculateDeliveryAmountController {

	@Autowired
	private DeliveryFacade deliveryFacade;
	
	@RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> create(@Valid @RequestBody Delivery delivery, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("JSON Validation Error", HttpStatus.BAD_REQUEST);
		}

		BigDecimal amount = deliveryFacade.calculate(delivery);
		System.out.println("Value -->" + amount.toString());
		if (!amount.equals(BigDecimal.ZERO)) {
			return new ResponseEntity<String>("Min Path Amount ---> " + amount.toString(), HttpStatus.OK);
		} else { 		
			return new ResponseEntity<String>("Error calculate min distance", HttpStatus.BAD_REQUEST);	
		}
	}
	
}
