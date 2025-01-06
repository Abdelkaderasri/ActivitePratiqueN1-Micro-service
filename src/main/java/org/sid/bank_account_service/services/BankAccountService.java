package org.sid.bank_account_service.services;

import org.sid.bank_account_service.dto.BankAccountDTO;
import org.sid.bank_account_service.dto.CreateBankAccountDTO;
import org.sid.bank_account_service.dto.UpdateBankAccountDTO;
import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.mappers.BankAccountMapper;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankAccountMapper bankAccountMapper;

    public List<BankAccountDTO> getAllAccounts() {
        List<BankAccount> accounts = bankAccountRepository.findAll();
        return accounts.stream()
                .map(bankAccountMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BankAccountDTO getAccountById(String id) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));
        return bankAccountMapper.toDTO(account);
    }

    public BankAccountDTO createAccount(CreateBankAccountDTO createBankAccountDTO) {
        BankAccount bankAccount = bankAccountMapper.toEntity(createBankAccountDTO);
        bankAccount.setCreatedAt(new Date());
        BankAccount savedAccount = bankAccountRepository.save(bankAccount);
        return bankAccountMapper.toDTO(savedAccount);
    }

    public BankAccountDTO updateAccount(String id, UpdateBankAccountDTO updateBankAccountDTO) {
        BankAccount account = bankAccountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Compte non trouvé"));

        bankAccountMapper.toEntity(updateBankAccountDTO, account); // Mise à jour de l'entité existante
        account.setCreatedAt(new Date());

        BankAccount updatedAccount = bankAccountRepository.save(account);
        return bankAccountMapper.toDTO(updatedAccount);
    }

    public void deleteAccount(String id) {
        bankAccountRepository.deleteById(id);
    }
}