package com.devsuperior.dsmeta.projection;

import java.time.LocalDate;

public interface SaleMinProjection {

        Long getId();
        LocalDate getDate();
        Double getAmount();
        String getSellerName();  // precisa bater com o alias na query

}
