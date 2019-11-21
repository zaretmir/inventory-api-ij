package com.app.hangars.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.base.hangar.model.Hangar;

public interface HangarDAO {
	
	List <Hangar> getHangars();
	
	Hangar getHangarById(Long hangarId);
	
	Hangar findById(long id);
	
	Boolean existsById(long id);

	Boolean existsHangarByName(String name);

	Hangar save(Hangar hangar);

	List<Hangar> findByIsStateTrue();

	Page<Hangar> getActiveHangarsPage(Pageable pageRequest);

	List<Hangar> findByIsStateTrueAndNameContaining(String search);

}
