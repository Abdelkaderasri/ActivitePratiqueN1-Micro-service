package org.sid.bank_account_service;

import org.sid.bank_account_service.entities.BankAccount;
import org.sid.bank_account_service.enums.AccountType;
import org.sid.bank_account_service.repositories.BankAccountRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;

@SpringBootApplication
public class BankAccountServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAccountServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BankAccountRepository bankAccountRepository) {
		return args -> {
			for (int i = 0; i < 10; i++) {
				BankAccount bankAccount = new BankAccount(
						UUID.randomUUID(),                  // id as UUID
						new Date(),                          // createdAt
						1000.0 * i,                         // balance
						"MAD",                              // currency
						Math.random() > 0.5 ? AccountType.CURRENT_ACCOUNT : AccountType.SAVING_ACCOUNT  // dynamic AccountType
				);
				bankAccountRepository.save(bankAccount);
			}
		};
	}
}
