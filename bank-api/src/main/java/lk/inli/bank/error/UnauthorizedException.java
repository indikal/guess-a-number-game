package lk.inli.bank.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

  private static final long serialVersionUID = -8807545435337866456L;

  public UnauthorizedException(String message) {
    super(message);
  }

}

