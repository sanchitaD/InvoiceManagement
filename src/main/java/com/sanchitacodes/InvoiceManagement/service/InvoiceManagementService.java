package com.sanchitacodes.InvoiceManagement.service;

import com.sanchitacodes.ClientManagement.entity.ClientDetail;
import com.sanchitacodes.ClientManagement.service.ClientManagementServiceImpl;
import com.sanchitacodes.InvoiceManagement.entity.Invoice;
import com.sanchitacodes.InvoiceManagement.entity.TransactionDetail;
import com.sanchitacodes.InvoiceManagement.repository.InvoiceManagementRepository;
import com.sanchitacodes.InvoiceManagement.repository.TransactionManagementRepository;
import com.sanchitacodes.InvoiceManagement.utility.InvoiceManagementUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
public class InvoiceManagementService {
    @Autowired
    private TransactionManagementRepository transactionManagementRepository;
    @Autowired
    private InvoiceManagementRepository invoiceManagementRepository;

    public List<TransactionDetail> saveTransactionDetail(List<TransactionDetail> transactionDetailList) {
        log.info("Inside saveTransactionDetail of InvoiceManagementService, saving records!");
        return transactionManagementRepository.saveAll(transactionDetailList);
    }

    public List<TransactionDetail> fetchTransactionDetail() {
        log.info("Inside fetchTransactionDetail of InvoiceManagementService, fetching records!");
        return transactionManagementRepository.findAll();
    }

    public TransactionDetail updateTransactionDetail(TransactionDetail transactionDetail, int id) {
        log.info("Inside updateTransactionDetail of InvoiceManagementService, updating records!");
        //TODO: update with id
        return transactionManagementRepository.save(transactionDetail);
    }

    public void deleteTransactionDetailById(int id) {
        log.info("Inside deleteTransactionDetailById of InvoiceManagementService, deleting records!");
        transactionManagementRepository.deleteById(id);
    }

    public String invoiceGeneration(int orderId) {
        log.info("Inside invoiceGeneration of InvoiceManagementService, generating invoice!");
        Optional<TransactionDetail> transactionDetail = transactionManagementRepository.findById(orderId);
        ClientDetail clientDetail = getClientDetail(transactionDetail.get().getClient());
        saveInvoiceDetails(orderId, transactionDetail.get().getAmount(), transactionDetail.get().getClient(),
                clientDetail.getFees(), clientDetail.getBilling_interval(), transactionDetail.get().getStatus());

        return "Invoice Generated!!";
    }

    private void saveInvoiceDetails(int orderId, float amount, String clientName,
                                    float fees, String billing_interval, String tx_status) {

        log.info("Inside saveInvoiceDetails of InvoiceManagementService, saving invoice in DB!");
        final String APPROVED = "approved";

        //TODO: create constant
        final String REFUND = "refund";                 //TODO: create constant
        final String DECLINED = "declined";             //TODO: create constant
        InvoiceManagementUtil invoiceManagementUtil = new InvoiceManagementUtil();
        Invoice invoice = new Invoice();
        if(tx_status.equals(APPROVED)) {
            invoice.setAmount(invoiceManagementUtil.calculateCharges(fees, amount));
        }
        if(tx_status.equals(REFUND)){
            invoice.setAmount(invoiceManagementUtil.calculateCharges(fees, amount));
        }
        if(tx_status.equals(DECLINED)){
            //TODO: Refund cannot be done for a declined transaction.
            invoice.setAmount(invoiceManagementUtil.calculateCharges(fees, amount));
        }
        invoice.setBilling_interval(billing_interval);
        invoice.setClientName(clientName);
        invoice.setOrderId(orderId);
        invoice.setStatus(tx_status);
        invoiceManagementRepository.save(invoice);
    }

    private ClientDetail getClientDetail(String clientName) {
        log.info("Inside getClientDetail of InvoiceManagementService, gettingClientDetails from DB!");
        ClientManagementServiceImpl clientManagementServiceImpl = new ClientManagementServiceImpl();
        ClientDetail clientDetail = clientManagementServiceImpl.fetchClientDetailByClientName(clientName);
        return clientDetail;
    }
}
