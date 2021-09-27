package lk.inli.bank.domain;

import java.util.UUID;

public class Account {
  private String id;
  private String userName;
  private String firstName;
  private String lastName;
  private int balance;

  public Account() {
    this.id = UUID.randomUUID().toString();
  }

  public Account(String userName) {
    this.id = UUID.randomUUID().toString();
    this.userName = userName;
    this.balance = 1000;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public int getBalance() {
    return balance;
  }

  public void setBalance(int balance) {
    this.balance = balance;
  }
}
