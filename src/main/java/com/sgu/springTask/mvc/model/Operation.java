package com.sgu.springTask.mvc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "operation")
public class Operation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column
    private Date date;
    @Column
    private String accCode;
    @Column
    private Long idAccountFrom;
    @Column
    private Long idAccountTo;
    @Column
    private double sum;
    @Column
    private double amountBefore;
    @Column
    private double amountAfter;

    public Operation(int id, Date date, String accCode, Long idAccountFrom,
                     Long idAccountTo, double sum, double amountBefore, double amountAfter) {
        this.id = id;
        this.date = date;
        this.accCode = accCode;
        this.idAccountFrom = idAccountFrom;
        this.idAccountTo = idAccountTo;
        this.sum = sum;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public Operation(Date date, String accCode, Long idAccountFrom,
                     Long idAccountTo, double sum, double amountBefore, double amountAfter) {
        this.id = -1;
        this.date = date;
        this.accCode = accCode;
        this.idAccountFrom = idAccountFrom;
        this.idAccountTo = idAccountTo;
        this.sum = sum;
        this.amountBefore = amountBefore;
        this.amountAfter = amountAfter;
    }

    public Operation() {
    }

    @Override
    public String toString() {
        return "id = " + id +
                ", дата перевода = '" + date + '\'' +
                ", валюта = '" + accCode + '\'' +
                ", счет с которого перевели = " + idAccountFrom +
                ", счет на который перевели = " + idAccountTo +
                ", сумма перевода = " + sum +
                ", Количетво средст до перевода = " + amountBefore +
                ", Количество после перевода = " + amountAfter;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAccCode() {
        return accCode;
    }

    public void setAccCode(String accCode) {
        this.accCode = accCode;
    }

    public Long getIdAccountFrom() {
        return idAccountFrom;
    }

    public void setIdAccountFrom(Long idAccountFrom) {
        this.idAccountFrom = idAccountFrom;
    }

    public Long getIdAccountTo() {
        return idAccountTo;
    }

    public void setIdAccountTo(Long idAccountTo) {
        this.idAccountTo = idAccountTo;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAmountBefore() {
        return amountBefore;
    }

    public void setAmountBefore(double amountBefore) {
        this.amountBefore = amountBefore;
    }

    public double getAmountAfter() {
        return amountAfter;
    }

    public void setAmountAfter(double amountAfter) {
        this.amountAfter = amountAfter;
    }
}
