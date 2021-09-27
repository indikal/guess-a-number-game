package lk.inli.randomnumber.service;

import lk.inli.randomnumber.domain.Account;

public interface BankService {
  String sayHello();

  Account getAccount(String id);

  Account createNewAccount(String name);

  Account creditGivenAccount(String id, int amount);

  Account debitGivenAccount(String id, int amount);
}
