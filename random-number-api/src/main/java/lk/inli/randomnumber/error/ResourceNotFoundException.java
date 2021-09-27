package lk.inli.randomnumber.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {

  private static final long serialVersionUID = -8696509168909378287L;

  /**
   * Resource not found thrown back.
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
