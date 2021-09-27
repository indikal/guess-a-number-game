package lk.inli.randomnumber.service.impl;

import lk.inli.randomnumber.domain.Account;
import lk.inli.randomnumber.error.FeatureNotSupportedException;
import lk.inli.randomnumber.repository.AccountRepository;
import lk.inli.randomnumber.service.BankService;
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
  public Account createNewAccount(String name) {
    return this.accountRepository.create(name);
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