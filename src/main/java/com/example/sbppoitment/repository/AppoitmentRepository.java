package com.example.sbppoitment.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sbppoitment.model.Appoitment;

@Repository
@Qualifier("appRepo")
public interface AppoitmentRepository extends JpaRepository<Appoitment,Long> {
  List<Appoitment>findAllByUsername(String username)	;
	

}
