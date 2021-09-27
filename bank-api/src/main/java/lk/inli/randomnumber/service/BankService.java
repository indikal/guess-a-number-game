package lk.inli.randomnumber.service;

import lk.inli.randomnumber.domain.Account;

public interface BankService {
  String sayHello();

  Account getAccount(String id);

  Account getAccountByUsername(String username);

  Account createNewAccount(Account account);

  Account creditGivenAccount(String id, int amount);

  Account debitGivenAccount(String id, int amount);
}
