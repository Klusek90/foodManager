package com.scorac.stockmanager.model.TDO;

import lombok.Data;

@Data
public class SaleDTO {
    private  int multiplicity;
    private  Long recipeid;

    public SaleDTO(int multiplicity, Long recipeid) {
        this.multiplicity = multiplicity;
        this.recipeid = recipeid;
    }
}
