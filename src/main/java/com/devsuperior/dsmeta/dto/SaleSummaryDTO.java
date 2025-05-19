package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.projection.SaleSummaryProjection;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SaleSummaryDTO {


    @JsonProperty("total")
    private Double amount;
    private String sellerName;

    public SaleSummaryDTO(SaleSummaryProjection projection) {
        amount = projection.getTotal();
        sellerName = projection.getSellerName();
    }

    public Double getAmount() {
        return amount;
    }

    public String getSellerName() {
        return sellerName;
    }
}
