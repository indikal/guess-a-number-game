package lk.inli.signup.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRequestException extends RuntimeException {

  private static final long serialVersionUID = 557458939223462960L;

  /**
   * Conflict resource request thrown back.
   */
  public InvalidRequestException(String message) {
    super(message);
  }

}
