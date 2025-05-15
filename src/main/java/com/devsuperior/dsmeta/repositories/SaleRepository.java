package com.devsuperior.dsmeta.repositories;

<<<<<<< Updated upstream
import com.devsuperior.dsmeta.projection.SaleMinProjection;
import org.springframework.data.domain.Page;
=======
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
>>>>>>> Stashed changes
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Pageable;

import com.devsuperior.dsmeta.entities.Sale;
import org.springframework.data.jpa.repository.Query;

<<<<<<< Updated upstream
=======
import java.time.LocalDate;
>>>>>>> Stashed changes
import java.util.List;

public interface SaleRepository extends JpaRepository<Sale, Long> {

<<<<<<< Updated upstream
        @Query("SELECT obj.id AS id, obj.date AS date, obj.amount AS amount, obj.seller.name AS sellerName " +
                "FROM Sale obj JOIN obj.seller seller")
        Page<SaleMinProjection> salesReport(Pageable pageable);
=======

    @Query(value = "SELECT obj FROM Sale obj JOIN FETCH obj.seller " +
            "WHERE UPPER(obj.seller.name) LIKE UPPER(CONCAT('%', :name, '%')) " +
            "AND obj.date > :minDate " +
            "AND obj.date < :maxDate ",
            countQuery = "SELECT COUNT(obj) FROM Sale obj JOIN obj.seller")
    Page<Sale> salesReport(LocalDate minDate, LocalDate maxDate, String name, Pageable pageable);
>>>>>>> Stashed changes

}
