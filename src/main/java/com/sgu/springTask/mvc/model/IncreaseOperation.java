package com.sgu.springTask.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class IncreaseOperation {
    Long accountId;
    String accCode;
    Double amount;

    public Long getAccountId() {
        return accountId;
    }

    public String getAccCode() {
        return accCode;
    }

    public Double getAmount() {
        return amount;
    }
}
