package lk.inli.randomnumber.repository;

import lk.inli.randomnumber.domain.Account;

public interface AccountRepository {

  Account create(String name);

  Account getById(String id);

  Account credit(String id, int amount);

  Account debit(String id, int amount);
}
