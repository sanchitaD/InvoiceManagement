package com.sanchitacodes.InvoiceManagement.repository;

import com.sanchitacodes.InvoiceManagement.entity.TransactionDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionManagementRepository extends JpaRepository<TransactionDetail,Integer> {
}
