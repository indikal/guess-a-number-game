package lk.inli.numbergame.service;

import lk.inli.numbergame.dtos.AccountDto;

public interface NumberGameService {

  AccountDto registerUser(AccountDto accountDto);

  public AccountDto checkUser(String username);

  AccountDto play(String id, int myNum);
}
