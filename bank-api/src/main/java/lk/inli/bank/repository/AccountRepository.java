package lk.inli.bank.repository;

import lk.inli.bank.domain.Account;

public interface AccountRepository {

  Account create(Account account);

  Account getById(String id);

  Account getByUsername(String username);

  Account credit(String id, int amount);

  Account debit(String id, int amount);
}
