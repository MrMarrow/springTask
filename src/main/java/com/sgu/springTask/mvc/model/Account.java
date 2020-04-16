package com.sgu.springTask.mvc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long clientId;
    private double amount;
    private String accCode;

    public Account() {
    }

    public Account(Long id, Long clientId, double amount, String accCode) {
        this.id = id;
        this.clientId = clientId;
        this.amount = amount;
        this.accCode = accCode;
    }

    public Account(Long clientId, String accCode) {
        this.id = -1L;
        this.clientId = clientId;
        this.accCode = accCode;
        this.amount = 0.;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", счёт = " + amount +
                ", тип валюты = " + accCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }
}
