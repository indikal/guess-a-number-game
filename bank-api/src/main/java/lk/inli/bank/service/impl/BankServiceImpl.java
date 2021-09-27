package lk.inli.bank.service.impl;

import lk.inli.bank.domain.Account;
import lk.inli.bank.error.FeatureNotSupportedException;
import lk.inli.bank.repository.AccountRepository;
import lk.inli.bank.service.BankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankServiceImpl implements BankService {
  @Autowired
  private AccountRepository accountRepository;

  @Override
  public String sayHello() {
    throw new FeatureNotSupportedException();
  }

  @Override
  public Account getAccount(String id) {
    return this.accountRepository.getById(id);
  }

  @Override
  public Account getAccountByUsername(String username) {
    return this.accountRepository.getByUsername(username);
  }

  @Override
  public Account createNewAccount(Account account) {
    return this.accountRepository.create(account);
  }

  @Override
  public Account creditGivenAccount(String id, int amount) {
    return this.accountRepository.credit(id, amount);
  }

  @Override
  public Account debitGivenAccount(String id, int amount) {
    return this.accountRepository.debit(id, amount);
  }
}
