package com.empresa;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.base.hangar.model.Hangar;
import com.app.hangars.builder.HangarBuilder;
import com.app.hangars.dto.HangarDto;
import com.app.hangars.service.HangarService;
@RestController
@RequestMapping("/api/hangar-management/")
@CrossOrigin
public class HangarController {

	@Autowired
	private HangarService hangarService;

	@GetMapping("hangars")
	public ResponseEntity<List<HangarDto>> getHangars(@RequestParam(required = false) String name) {

		List<Hangar> results = new ArrayList<Hangar>();

		if (name != null) {
			results = hangarService.getActiveHangarsMatchingSearch(name);
		} else {
			results = hangarService.getActiveHangars();
		}

		List<HangarDto> response = results.stream()
				.map(hangar -> HangarBuilder.convertToDto(hangar))
				.collect(Collectors.toList());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("hangars/{id}")
	public ResponseEntity<HangarDto> getHangarById(@PathVariable("id") long id) {
		Hangar hangar = hangarService.getHangarById(id);
		HangarDto dto = HangarBuilder.convertToDto(hangar);

		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@GetMapping("hangars/{page}/{size}")
	public ResponseEntity<Page<HangarDto>> getHangarsPage(
			@PathVariable("page") int page,
			@PathVariable("size") int size) {

		Pageable pageRequest = PageRequest.of(page, size);

		Page<Hangar> hangars = hangarService.getActiveHangarsPage(pageRequest);

		Page<HangarDto> dtos = new PageImpl<HangarDto>(hangars.getContent().stream()
				.map(hangar -> HangarBuilder.convertToDto(hangar)).collect(Collectors.toList()),
				pageRequest,
				hangars.getTotalElements());

		return new ResponseEntity<>(dtos, HttpStatus.OK);
	}

	@PostMapping("hangars")
	public ResponseEntity<HangarDto> createHangar(@Valid @RequestBody HangarDto dto) {
		Hangar entity = HangarBuilder.convertToEntity(dto);
		Hangar saved = hangarService.createHangar(entity);
		HangarDto response = HangarBuilder.convertToDto(saved);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PutMapping("hangars/{id}")
	public ResponseEntity<Hangar> updateHangar(
			@PathVariable("id") Long id,
			@Valid @RequestBody HangarDto dto) {
		// ?
		Hangar update = HangarBuilder.convertToEntity(dto);

		return new ResponseEntity<>(hangarService.editHangar(id, update), HttpStatus.OK);
	}

	@PutMapping("hangars/delete/{id}")
	public ResponseEntity<HangarDto> deleteHangar(@PathVariable("id") Long id) {
		HangarDto responseHangar = HangarBuilder.convertToDto(hangarService.deleteHangar(id));
		return new ResponseEntity<>(responseHangar, HttpStatus.OK);
	}

}
