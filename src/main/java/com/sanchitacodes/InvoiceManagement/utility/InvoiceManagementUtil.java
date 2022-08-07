package com.sanchitacodes.InvoiceManagement.utility;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class InvoiceManagementUtil {

    public String calculateCharges(String currency, float fees, float amount) {
        log.info("Inside calculateCharges of InvoiceManagementUtil, calculating charges!");
        return amount + fees + " " + currency;
    }
}
