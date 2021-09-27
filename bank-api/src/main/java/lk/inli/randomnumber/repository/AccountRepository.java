package lk.inli.randomnumber.repository;

import lk.inli.randomnumber.domain.Account;

public interface AccountRepository {

  Account create(Account account);

  Account getById(String id);

  Account getByUsername(String username);

  Account credit(String id, int amount);

  Account debit(String id, int amount);
}
