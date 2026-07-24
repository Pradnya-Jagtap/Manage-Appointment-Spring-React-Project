package com.example.sbppoitment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.sbppoitment.model.Appoitment;
import com.example.sbppoitment.repository.AppoitmentRepository;
@Service
public class AppointmentServicesImpl implements AppoitmentServices {
@Autowired 

@Qualifier("appRepo")
private AppoitmentRepository appRepo;
	@Override
	public Appoitment insert(Appoitment ap) {
		// TODO Auto-generated method stub
		return appRepo.save(ap);
	}

	@Override
	public Appoitment search(Long apid) {
		// TODO Auto-generated method stub
		Appoitment ap= appRepo.findById(apid).orElse(null);
		return ap;
	}

	@Override
	public void delete(Long apid) {
		// TODO Auto-generated method stub
		appRepo.deleteById(apid);
	}

	@Override
	public Appoitment update(Appoitment ap) {
		// TODO Auto-generated method stub
		Appoitment apoId= appRepo.findById(ap.getAppid()).orElse(null);
		if(apoId!=null) {
			apoId.setAppdate(ap.getAppdate());
			apoId.setApptime(ap.getApptime());
			apoId.setComplainet(ap.getComplainet());
			apoId.setStatus(ap.getStatus());
			return appRepo.save(apoId);
		}
		return apoId;
	}

	@Override
	public List<Appoitment> getAll() {
		// TODO Auto-generated method stub
		return appRepo.findAll();
	}

	@Override
	public List<Appoitment> userappoitment(String uname) {
		// TODO Auto-generated method stub
		return appRepo.findAllByUsername(uname);
	}

}
