package lk.inli.signup.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends RuntimeException {

  private static final long serialVersionUID = -8807538241137866537L;

  public InternalServerErrorException(String message) {
    super(message);
  }

}

