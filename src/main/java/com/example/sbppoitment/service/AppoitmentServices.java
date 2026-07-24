package com.example.sbppoitment.service;

import java.util.List;

import com.example.sbppoitment.model.Appoitment;

public interface AppoitmentServices {
	Appoitment insert(Appoitment ap);
	Appoitment search(Long apid);
	void delete(Long apid);
	Appoitment update(Appoitment ap);
	List<Appoitment>getAll();
	List<Appoitment>userappoitment(String uname);

}
