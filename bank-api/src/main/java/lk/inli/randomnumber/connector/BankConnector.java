package lk.inli.randomnumber.connector;

import lk.inli.randomnumber.domain.Account;
import lk.inli.randomnumber.dtos.AccountDto;
import lk.inli.randomnumber.error.InvalidRequestException;
import lk.inli.randomnumber.service.BankService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/api/v1")
public class BankConnector {

  @Autowired
  private BankService bankService;

  private ModelMapper modelMapper = new ModelMapper();

  /**
   * List all courses for user.
   * @return
   */
  @GetMapping("/hello")
  public String helloBank() {
    return this.bankService.sayHello();
  }

  @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
  public AccountDto getUserAccount(@PathVariable String id) {
    if (null == id || id.isEmpty()) {
      throw new InvalidRequestException("Account ID is required");
    }

    Account account = this.bankService.getAccount(id);

    return modelMapper.map(account, AccountDto.class);
  }

  @RequestMapping(method = RequestMethod.POST, value = "/create")
  public AccountDto createUserAccount(@RequestParam String name) {
    if (null == name || name.isEmpty()) {
      throw new InvalidRequestException("Account name is required");
    }

    Account account = this.bankService.createNewAccount(name);

    return modelMapper.map(account, AccountDto.class);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}/credit")
  public AccountDto creditUserAccount(@PathVariable String id, @RequestParam int amount) {
    if (null == id || id.isEmpty()) {
      throw new InvalidRequestException("Account ID is required");
    }

    if (amount < 0) {
      throw new InvalidRequestException("Amount must be above zero");
    }

    Account account = this.bankService.creditGivenAccount(id, amount);

    return modelMapper.map(account, AccountDto.class);
  }

  @RequestMapping(method = RequestMethod.PUT, value = "/user/{id}/debit")
  public AccountDto debitUserAccount(@PathVariable String id, @RequestParam int amount) {
    if (null == id || id.isEmpty()) {
      throw new InvalidRequestException("Account ID is required");
    }

    if (amount < 0) {
      throw new InvalidRequestException("Amount must be above zero");
    }

    Account account = this.bankService.debitGivenAccount(id, amount);

    return modelMapper.map(account, AccountDto.class);
  }
}
