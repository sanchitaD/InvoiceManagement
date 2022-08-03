package com.sanchitacodes.InvoiceManagement.service;

import com.sanchitacodes.InvoiceManagement.entity.TransactionDetail;
import java.util.List;
public interface InvoiceManagementService {
    //Insert the transaction details
    TransactionDetail addTransactionDetail(TransactionDetail transactionDetail);

    //Get the list of transaction and its details
    List<TransactionDetail> fetchTransactionDetail();

    //Update the transaction details
    TransactionDetail updateTransactionDetail(TransactionDetail transactionDetail, Integer id);

    //Delete the transaction details
    void deleteTransactionDetailById(Integer id);
}
