package com.sanchitacodes.InvoiceManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail {
    @Id
    private Integer orderid;
    private Date datetime;
    private String ordername;
    private float amount;
    private String currency;
    private String cardtype;
    private String status;
    private String client;
}
