package com.sanchitacodes.InvoiceManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoiceDetail {
    @Id
    private int OrderId;
    private String clientName;
    private int billing_interval;
    private String charges;
    private String status;
    private Date billing_date;
}
