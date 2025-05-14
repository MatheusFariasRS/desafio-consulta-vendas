package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projection.SaleMinProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

        @Query("SELECT obj.id AS id, obj.date AS date, obj.amount AS amount, obj.seller.name AS sellerName " +
                "FROM Sale obj JOIN obj.seller seller")
        Page<SaleMinProjection> salesReport(Pageable pageable);

}
