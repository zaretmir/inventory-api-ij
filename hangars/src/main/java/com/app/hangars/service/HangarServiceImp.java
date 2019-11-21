package com.app.hangars.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.app.base.exception.ApplicationException;
import com.app.base.hangar.model.Hangar;
import com.app.hangars.dao.HangarDAO;
import com.app.hangars.exception.HangarExceptionCause;

@Service
public class HangarServiceImp implements HangarService {
	
	@Autowired
	HangarDAO hangarDAO;
	
	@Override
	public Hangar getHangarById(long id) {
		Hangar hangar = hangarDAO.getHangarById(id);
		if (hangar == null)
			throw new ApplicationException(HangarExceptionCause.NOT_FOUND);
		
		return hangar;
	}
	
	@Override
	public List<Hangar> getHangars() {
		List<Hangar> hangars = hangarDAO.getHangars();
		
		if (hangars == null || hangars.isEmpty())
			throw new ApplicationException(HangarExceptionCause.NOT_FOUND);
		
		return hangars;
	}
	
	@Override
	public List<Hangar> getActiveHangars() {
		List<Hangar> hangars = hangarDAO.findByIsStateTrue();
		
		if (hangars == null || hangars.isEmpty())
			throw new ApplicationException(HangarExceptionCause.NOT_FOUND_ACTIVE);
		
		return hangars;
	}
		
	@Override
	public Page<Hangar> getActiveHangarsPage(Pageable pageRequest) {
		Page<Hangar> hangarPage = hangarDAO.getActiveHangarsPage(pageRequest);
		
		if (hangarPage.getContent() == null || hangarPage.getContent().isEmpty())
			throw new ApplicationException(HangarExceptionCause.NOT_FOUND);
		
		return hangarPage;
	}

	@Override
	public List<Hangar> getActiveHangarsMatchingSearch(String search) {
		List<Hangar> hangars = hangarDAO.findByIsStateTrueAndNameContaining(search);
		
		if (hangars == null || hangars.isEmpty())
			throw new ApplicationException(HangarExceptionCause.NOT_FOUND_ACTIVE);
		
		return hangars;
	}
	
	@Override
	public Hangar createHangar(Hangar hangar) {
		String formattedName = hangar.getName().trim();
		hangar.setName(formattedName);
		
		if (hangarDAO.existsHangarByName(formattedName))
			throw new ApplicationException(HangarExceptionCause.NAME_DUPLICATED);	
		
		return hangarDAO.save(hangar);
	}

	@Override
	public Hangar editHangar(Long id, Hangar update) {
		Hangar hangar = hangarDAO.getHangarById(update.getId());
		
		if (hangar == null)
			throw new ApplicationException(HangarExceptionCause.NOT_FOUND);		
		
		hangar.setName(update.getName());
		hangar.setAddress(update.getAddress());
		hangar.setOwner(update.getOwner());
		hangar.setOwnerEmail(update.getOwnerEmail());
		hangar.setPhoneNumber(update.getPhoneNumber());
		
		return hangarDAO.save(hangar);
	}

	@Override
	public Hangar deleteHangar(Long id) {
		Hangar hangar = hangarDAO.getHangarById(id);		
		if (hangar == null)
			throw new ApplicationException(HangarExceptionCause.NOT_FOUND);
		
		hangar.setActive(false);
		
		return hangarDAO.save(hangar);
	}	

}
