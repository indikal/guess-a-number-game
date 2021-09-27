package lk.inli.randomnumber.connector;

import lk.inli.randomnumber.service.RandomNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/randomnumber/api/v1")
public class RandomNumberConnector {
  @Autowired
  private RandomNumberService randomNumberService;

  @RequestMapping(method = RequestMethod.GET, value = "/generate")
  public int generateNumber() {
    return randomNumberService.getNextNumber();
  }
}
