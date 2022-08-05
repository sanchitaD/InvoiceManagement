package com.sanchitacodes.InvoiceManagement.controller;

import com.sanchitacodes.InvoiceManagement.entity.TransactionDetail;
import com.sanchitacodes.InvoiceManagement.service.InvoiceManagementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
public class InvoiceController {

    @Autowired
    private InvoiceManagementService invoiceManagementService;

    @GetMapping("/transactionDetail")
    public List<TransactionDetail> fetchPaymentTransaction(){
        log.info("Inside fetchPaymentTransaction of InvoiceController.");
        return invoiceManagementService.fetchTransactionDetail();
    }

    @PostMapping("/transactionDetail")
    public List<TransactionDetail> savePaymentTransaction
            (@Valid @RequestBody List<TransactionDetail> transactionDetail){
        log.info("Inside savePaymentTransaction of InvoiceController.");
        return invoiceManagementService.saveTransactionDetail(transactionDetail);
    }

    @PutMapping("/transactionDetail/{id}")
    public TransactionDetail updatePaymentTransaction(@PathVariable("id") int id,
                                         @RequestBody TransactionDetail transactionDetail){
        log.info("Inside updatePaymentTransaction of InvoiceController.");
        return invoiceManagementService.updateTransactionDetail(transactionDetail,id);
    }

    @DeleteMapping("/transactionDetail/{id}")
    public String deletePaymentTransaction(@PathVariable("id") int id){
        log.info("Inside deletePaymentTransaction of InvoiceController.");
        invoiceManagementService.deleteTransactionDetailById(id);
        return "Deleted Successfully!!";
    }

    @GetMapping("/generateInvoice/{id}")
    public String generateInvoiceByOrderId(@PathVariable("id") int orderId){
        log.info("Inside generateInvoiceByOrderId of InvoiceController.");
        return invoiceManagementService.invoiceGeneration(orderId);
    }
}
