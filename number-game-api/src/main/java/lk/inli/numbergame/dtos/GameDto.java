package lk.inli.numbergame.dtos;

public class GameDto {
  private String accountId;
  private int guessedNumber;

  public String getAccountId() {
    return accountId;
  }

  public void setAccountDto(String accountId) {
    this.accountId = accountId;
  }

  public int getGuessedNumber() {
    return guessedNumber;
  }

  public void setGuessedNumber(int guessedNumber) {
    this.guessedNumber = guessedNumber;
  }
}
