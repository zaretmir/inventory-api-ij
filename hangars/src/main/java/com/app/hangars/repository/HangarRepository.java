package com.app.hangars.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.app.base.hangar.model.Hangar;

public interface HangarRepository extends JpaRepository<Hangar, Long> {
	
	Optional<Hangar> findById(long id);
	
	boolean existsHangarByName(String name);
	
	List<Hangar> findByIsActiveTrue();
	
	Page<Hangar> findByIsActiveTrue(Pageable pageable); // Revisar import
	
	List<Hangar> findByNameContaining(String search);
	
	List<Hangar> findByIsActiveTrueAndNameContaining(String search);
}
