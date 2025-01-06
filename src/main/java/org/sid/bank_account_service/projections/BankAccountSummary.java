package org.sid.bank_account_service.projections;

import org.sid.bank_account_service.entities.BankAccount;
import org.springframework.data.rest.core.config.Projection;


@Projection(name = "bankAccountSummary", types = BankAccount.class)
public interface BankAccountSummary {
    String getId();
    Double getBalance();
    String getCurrency();
}