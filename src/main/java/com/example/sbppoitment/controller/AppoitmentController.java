package com.example.sbppoitment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sbppoitment.model.Appoitment;
import com.example.sbppoitment.service.AppoitmentServices;

@RestController
@RequestMapping("/api/")
public class AppoitmentController {
	@Autowired
	private AppoitmentServices appservice;
	
	@PostMapping("/appointments")
	public Appoitment insert(@RequestBody Appoitment ap)
	{
		return appservice.insert(ap);
	}
	
	@GetMapping("/appointments")
	public List<Appoitment> getAll()
	{
		return appservice.getAll();
	}
	@GetMapping("/appointments/{apid}")
	public Appoitment search (@PathVariable ("apid") Long apid) {
		return appservice.search(apid);
	}
	@DeleteMapping("/appointments/{apid}")
	public ResponseEntity<Void> delete (@PathVariable("apid") Long apid){
		
		appservice.delete(apid);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		
	}
	/*@PutMapping("/appointments/{apid}")
	public Appoitment update(@PathVariable("apid")Long apid, @RequestBody Appoitment ap) {
		ap.setId(apid);
		return appservice.update(ap);
	}*/
	@PutMapping("/appointments/{apid}")
	public Appoitment update(@PathVariable("apid") Long apid, @RequestBody Appoitment ap) {
	    ap.setAppid(apid); // This will now map flawlessly to Hibernate's identity check
	    return appservice.update(ap);
	}

	@GetMapping("/userappointments/{uname}")
	public List<Appoitment>getbyuser(@PathVariable("uname") String  uname){
		return appservice.userappoitment(uname);
	}

}
