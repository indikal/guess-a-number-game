package lk.inli.randomnumber.service.impl;

import java.util.Random;
import lk.inli.randomnumber.service.RandomNumberService;
import org.springframework.stereotype.Service;

@Service
public class RandomNumberServiceImpl implements RandomNumberService {

  private final int MAX = 10;
  private final int MIN = 0;

  @Override
  public int getNextNumber() {
    return new Random().nextInt(MAX - MIN + 1) + MIN;
  }
}
