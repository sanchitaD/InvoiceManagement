package com.sanchitacodes.InvoiceManagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    private int OrderId;
    private String clientName;
    private String billing_interval;
    private float amount;
    private String status;
}
