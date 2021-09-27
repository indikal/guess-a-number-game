package lk.inli.numbergame.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
public class FeatureNotSupportedException extends RuntimeException {

  /**
   * Feature not supported thrown back.
   */
  public FeatureNotSupportedException() {
    super("This feature is not supported yet");
  }
}
