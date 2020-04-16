package com.sgu.springTask.mvc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactOperation {

    private Long accountId;
    private String phone;
    private String accCode;
    private Double amount;

    public Long getAccountId() {
        return accountId;
    }

    public String getPhone() {
        return phone;
    }

    public String getAccCode() {
        return accCode;
    }

    public Double getAmount() {
        return amount;
    }
}
