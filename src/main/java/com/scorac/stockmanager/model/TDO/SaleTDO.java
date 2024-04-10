package com.scorac.stockmanager.model.TDO;

import lombok.Data;

@Data
public class SaleTDO {
    private  int multiplicity;
    private  Long recipeid;

    public SaleTDO(int multiplicity, Long recipeid) {
        this.multiplicity = multiplicity;
        this.recipeid = recipeid;
    }
}
