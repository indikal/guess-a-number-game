package lk.inli.randomnumber.repository.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import lk.inli.randomnumber.domain.Account;
import lk.inli.randomnumber.error.InvalidRequestException;
import lk.inli.randomnumber.error.ResourceNotFoundException;
import lk.inli.randomnumber.repository.AccountRepository;
import org.springframework.stereotype.Component;

@Component
public class AccountRepositoryImpl implements AccountRepository {
  private static final Map<String, Account> accounts = new ConcurrentHashMap<>();

  @Override
  public Account create(Account newAccount) {
    try {
      Account existingAccount = this.getByUsername(newAccount.getUserName());

      if (null != existingAccount) {
        throw new InvalidRequestException("Username already exist");
      }
    } catch (ResourceNotFoundException ignore) {
      //no user exist so we can create the user safely
    }

    Account account = new Account(newAccount.getUserName());
    account.setFirstName(newAccount.getFirstName());
    account.setLastName(newAccount.getLastName());

    accounts.put(account.getId(), account);

    return account;
  }

  @Override
  public Account getById(String id) {
    Account account = accounts.get(id);

    if (null == account) {
      throw new ResourceNotFoundException("No account found for id: " + id);
    }
    return account;
  }

  @Override
  public Account getByUsername(String username) {
    Optional<Account> account = accounts.values().stream()
        .filter(acct -> acct.getUserName().equalsIgnoreCase(username))
        .findFirst();

    if (account.isEmpty()) {
      throw new ResourceNotFoundException("No account found for username: " + username);
    }
    return account.get();
  }

  @Override
  public Account credit(String id, int amount) {
    if (amount < 0) {
      throw new InvalidRequestException("Amount must be above zero");
    }

    Account account = this.getById(id);
    int balance = account.getBalance();
    balance -= amount;

    if (balance < 0) {
      throw new InvalidRequestException("No enough credit to perform the transaction");
    }
    account.setBalance(balance);

    return account;
  }

  @Override
  public Account debit(String id, int amount) {
    if (amount < 0) {
      throw new InvalidRequestException("Amount must be above zero");
    }

    Account account = this.getById(id);
    int balance = account.getBalance();
    balance += amount;
    account.setBalance(balance);

    return account;
  }
}
