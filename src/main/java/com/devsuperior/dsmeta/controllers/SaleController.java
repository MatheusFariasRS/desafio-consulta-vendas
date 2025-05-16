package com.devsuperior.dsmeta.controllers;

import com.devsuperior.dsmeta.dto.SaleReportDTO;
import com.devsuperior.dsmeta.projection.SaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.services.SaleService;

import java.util.List;

@RestController
@RequestMapping(value = "/sales")
public class SaleController {

	@Autowired
	private SaleService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<SaleMinDTO> findById(@PathVariable Long id) {
		SaleMinDTO dto = service.findById(id);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/report")
	public ResponseEntity<Page<SaleReportDTO>> getReport(
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "size", defaultValue = "12") int size)
	{
		PageRequest pageRequest = PageRequest.of(page, size);
		Page<SaleReportDTO> report =  service.report(minDate, maxDate, name, pageRequest);
		return ResponseEntity.ok(report);
	}

	@GetMapping(value = "/summary")
	public ResponseEntity<List<SaleSummaryProjection>> getSummary(
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {

		List<SaleSummaryProjection> list = service.summary(minDate, maxDate);

		return ResponseEntity.ok(list);
	}
}
