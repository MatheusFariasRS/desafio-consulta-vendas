package com.devsuperior.dsmeta.services;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

<<<<<<< Updated upstream
import com.devsuperior.dsmeta.projection.SaleMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
=======
import com.devsuperior.dsmeta.dto.SaleReportDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
import org.springframework.stereotype.Service;

import com.devsuperior.dsmeta.dto.SaleMinDTO;
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
<<<<<<< Updated upstream

	public Page<SaleMinDTO> find(PageRequest pageRequest) {
		Page<SaleMinProjection> page = repository.salesReport(pageRequest);
		return page.map(SaleMinDTO::new);  // Correção: não é necessário cast para Page<SaleMinDTO>
	}

=======
	public Page<SaleReportDTO> report(String minDateStr, String maxDateStr, String name, Pageable pageable){

		LocalDate minDate = minDateStr.isEmpty() ? LocalDate.of(1900, 1, 1) : LocalDate.parse(minDateStr);
		LocalDate maxDate = maxDateStr.isEmpty() ? LocalDate.now() : LocalDate.parse(maxDateStr);

		Page<Sale> list = repository.salesReport(minDate, maxDate, name, pageable);
		return list.map(x -> new SaleReportDTO(x));
	}
>>>>>>> Stashed changes
}
