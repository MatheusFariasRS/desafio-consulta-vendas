package com.devsuperior.dsmeta.services;

import java.util.Optional;
import java.util.stream.Collectors;

import com.devsuperior.dsmeta.projection.SaleMinProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

	public Page<SaleMinDTO> find(PageRequest pageRequest) {
		Page<SaleMinProjection> page = repository.salesReport(pageRequest);
		return page.map(SaleMinDTO::new);  // Correção: não é necessário cast para Page<SaleMinDTO>
	}

}
