package lk.inli.signup.service;

import lk.inli.signup.dtos.AccountDto;

public interface NumberGameService {

  AccountDto registerUser(String name);

  AccountDto play(String id, int myNum);
}
