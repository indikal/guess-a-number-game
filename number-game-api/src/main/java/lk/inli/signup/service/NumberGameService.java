package lk.inli.signup.service;

import lk.inli.signup.dtos.AccountDto;

public interface NumberGameService {

  AccountDto registerUser(AccountDto accountDto);

  public AccountDto checkUser(String username);

  AccountDto play(String id, int myNum);
}
