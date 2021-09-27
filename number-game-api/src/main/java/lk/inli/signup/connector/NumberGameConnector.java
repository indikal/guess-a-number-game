package lk.inli.signup.connector;

import lk.inli.signup.dtos.AccountDto;
import lk.inli.signup.dtos.GameDto;
import lk.inli.signup.error.InvalidRequestException;
import lk.inli.signup.service.NumberGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/numbergame/api/v1")
public class NumberGameConnector {
  @Autowired
  private NumberGameService numberGameService;

  @RequestMapping(method = RequestMethod.POST, value = "/play")
  public AccountDto playTheGame(@RequestBody GameDto gameDto) {
    if (null == gameDto || null == gameDto.getAccountId() || gameDto.getAccountId().isEmpty()) {
      throw new InvalidRequestException("Account ID is required");
    }

    if (gameDto.getGuessedNumber() < 0 || gameDto.getGuessedNumber() > 10) {
      throw new InvalidRequestException("A valid number between 0 to 10 is required");
    }

    return this.numberGameService.play(gameDto.getAccountId(), gameDto.getGuessedNumber());
  }

  @RequestMapping(method = RequestMethod.POST, value = "/register")
  public AccountDto registerUser(@RequestBody AccountDto accountDto) {
    if (null == accountDto || accountDto.getUserName().isEmpty() || accountDto.getFirstName()
        .isEmpty() || accountDto.getLastName().isEmpty()) {
      throw new InvalidRequestException("Account details are required");
    }

    return this.numberGameService.registerUser(accountDto);
  }

  @RequestMapping(method = RequestMethod.GET, value = "/user")
  public AccountDto checkUser(@RequestParam String username) {
    if (null == username || username.isEmpty()) {
      throw new InvalidRequestException("Account username is required");
    }

    return this.numberGameService.checkUser(username);
  }
}
