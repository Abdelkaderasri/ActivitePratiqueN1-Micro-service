package org.sid.bank_account_service.web;

import org.sid.bank_account_service.dto.BankAccountDTO;
import org.sid.bank_account_service.dto.CreateBankAccountDTO;
import org.sid.bank_account_service.dto.UpdateBankAccountDTO;
import org.sid.bank_account_service.services.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class BankAccountGraphQLController {

    @Autowired
    private BankAccountService bankAccountService;

    // Query to get all accounts
    @QueryMapping
    public List<BankAccountDTO> getAllAccounts() {
        return bankAccountService.getAllAccounts();
    }

    // Query to get a specific account by ID
    @QueryMapping
    public BankAccountDTO getAccountById(@Argument String id) {
        return bankAccountService.getAccountById(id);
    }

    // Mutation to create a new account
    @MutationMapping
    public BankAccountDTO createAccount(
            @Argument CreateBankAccountDTO input) {
        return bankAccountService.createAccount(input);
    }

    // Mutation to update an account
    @MutationMapping
    public BankAccountDTO updateAccount(
            @Argument String id,
            @Argument UpdateBankAccountDTO input) {
        return bankAccountService.updateAccount(id, input);
    }

    // Mutation to delete an account
    @MutationMapping
    public Boolean deleteAccount(@Argument String id) {
        bankAccountService.deleteAccount(id);
        return true;
    }
}
