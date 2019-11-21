package com.app.hangars.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.app.base.hangar.model.Hangar;
import com.app.hangars.repository.HangarRepository;

@Component
public class HangarDAOImp implements HangarDAO {
	
	@Autowired
	HangarRepository hangarRepository;

	@Override
	public List<Hangar> getHangars() {
		return hangarRepository.findAll();
	}
	
	@Override
	public List<Hangar> findByIsStateTrue() {
		return hangarRepository.findByIsActiveTrue();
	}
	
	@Override
	public Hangar getHangarById(Long hangarId) {
		return hangarRepository.getOne(hangarId);
	}
	
	public Hangar save(Hangar hangar) {
		return hangarRepository.save(hangar);
	}
	
	@Override
	public Hangar findById(long id) {
		return hangarRepository.findById(id).get();
	}
	
	@Override
	public Boolean existsById(long id) {
		return hangarRepository.existsById(id);
	}
	
	@Override
	public Boolean existsHangarByName(String name) {
		return hangarRepository.existsHangarByName(name);
	}

	@Override
	public Page<Hangar> getActiveHangarsPage(Pageable pageRequest) {
		return hangarRepository.findByIsActiveTrue(pageRequest);
	}

	@Override
	public List<Hangar> findByIsStateTrueAndNameContaining(String search) {
		return hangarRepository.findByIsActiveTrueAndNameContaining(search);
	}

}
