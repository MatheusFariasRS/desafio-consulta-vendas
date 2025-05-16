package com.devsuperior.dsmeta.repositories;

import com.devsuperior.dsmeta.projection.SaleSummaryProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {


    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller " +
            "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
            "AND obj.date BETWEEN :minDate AND :maxDate ",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller")
    Page<Sale> salesReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);

    @Query("SELECT obj.seller.name AS sellerName, SUM(obj.amount) AS total " +
            "FROM Sale obj " +
            "WHERE obj.date BETWEEN :minDate AND :maxDate " +
            "GROUP BY obj.seller.id")
    List<SaleSummaryProjection> salesSummary(LocalDate minDate, LocalDate maxDate);

}

