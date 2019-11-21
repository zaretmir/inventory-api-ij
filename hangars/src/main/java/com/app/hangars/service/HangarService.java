package com.app.hangars.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.app.base.hangar.model.Hangar;
public interface HangarService {
	
	List<Hangar> getHangars();
	
	Hangar createHangar(@Valid Hangar hangar);

	Hangar getHangarById(long id);

	Hangar editHangar(Long id, Hangar update);

	Hangar deleteHangar(Long id);

	List<Hangar> getActiveHangars();

	Page<Hangar> getActiveHangarsPage(Pageable pageRequest);

	List<Hangar> getActiveHangarsMatchingSearch(String term);


}
