package com.devsuperior.dsmeta.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
import com.devsuperior.dsmeta.projection.SaleSummaryProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.repositories.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository repository;

	public SaleMinDTO findById(Long id) {
		Optional<Sale> result = repository.findById(id);
		Sale entity = result.get();
		return new SaleMinDTO(entity);
	}


	public Page<SaleMinDTO> report(String minDateStr, String maxDateStr, String name, Pageable pageable) {
		LocalDate maxDate;
		if (maxDateStr == null || maxDateStr.isEmpty()) {
			maxDate = LocalDate.now();
		} else {
			maxDate = LocalDate.parse(maxDateStr);
		}
		LocalDate minDate;
		if (minDateStr == null || minDateStr.isEmpty()) {
			minDate = maxDate.minusYears(1L);
		} else {
			minDate = LocalDate.parse(minDateStr);
		}
		String nomeTratado;
		if (name == null || name.trim().isEmpty()) {
			nomeTratado = "";
		} else {
			nomeTratado = name.trim();
		}

		Page<Sale> list = repository.salesReport(minDate, maxDate, nomeTratado, pageable);
		return list.map(SaleMinDTO::new);
	}


	public List<SaleSummaryProjection> summary(String minDateStr, String maxDateStr){

		LocalDate maxDate;
		if (maxDateStr == null || maxDateStr.isEmpty()) {
			maxDate = LocalDate.now();
		} else {
			maxDate = LocalDate.parse(maxDateStr);
		}
		LocalDate minDate;
		if (minDateStr == null || minDateStr.isEmpty()) {
			minDate = maxDate.minusYears(1L);
		} else {
			minDate = LocalDate.parse(minDateStr);
		}

		return repository.salesSummary(minDate, maxDate);
	}


}
