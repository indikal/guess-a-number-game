package lk.inli.randomnumber.domain;

import java.util.UUID;

public class Account {
  private String id;
  private String name;
  private int balance;

  public Account() {
    this.id = UUID.randomUUID().toString();
  }

  public Account(String name) {
    this.id = UUID.randomUUID().toString();
    this.name = name;
    this.balance = 1000;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
