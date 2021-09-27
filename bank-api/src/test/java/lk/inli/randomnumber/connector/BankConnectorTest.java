package lk.inli.randomnumber.connector;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lk.inli.randomnumber.error.FeatureNotSupportedException;
import lk.inli.randomnumber.service.BankService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class BankConnectorTest {
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private BankService bankService;

  @Test
  public void testHelloBank_returnException() throws Exception {

    doThrow(new FeatureNotSupportedException()).when(bankService).sayHello();

    mockMvc.perform(get("/bank/api/v1/hello"))
        // Validate the response code and content type
        .andExpect(status().is5xxServerError());
  }

  @Test
  public void testHelloBank() throws Exception {

    doReturn("Hello").when(bankService).sayHello();

    mockMvc.perform(get("/bank/api/v1/hello"))
        // Validate the response code and content type
        .andExpect(status().isOk())
        .andExpect(content().string("Hello"));
  }
}
