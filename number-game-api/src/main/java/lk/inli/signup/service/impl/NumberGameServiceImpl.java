package lk.inli.signup.service.impl;

import java.util.List;
import java.util.Objects;
import javax.annotation.PostConstruct;
import lk.inli.signup.dtos.AccountDto;
import lk.inli.signup.error.InternalServerErrorException;
import lk.inli.signup.error.InvalidRequestException;
import lk.inli.signup.service.NumberGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class NumberGameServiceImpl implements NumberGameService {
  private final int COST_PER_SINGLE_PLAY = 10;
  private enum RESULT { WIN, LOSS };

  @Autowired
  private RestTemplateBuilder restTemplateBuilder;

  private RestTemplate restTemplate;

  @Value("${bank.api.base.uri}")
  private String bankApiBaseUrl;

  @Value("${randomnumber.api.base.uri}")
  private String randomNumberApiBaseUrl;

  @PostConstruct
  public void afterPropertiesSet() {
    restTemplate = restTemplateBuilder.build();
  }

  @Override
  public AccountDto registerUser(String name) {

    HttpHeaders headers = this.getHeaders();
    HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

    //adding the query params to the URL
    UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(
        this.bankApiBaseUrl + "/create")
        .queryParam("name", name);

    ResponseEntity<AccountDto> responseEntity =
        restTemplate.exchange(
            uriBuilder.toUriString(), HttpMethod.POST, requestEntity, AccountDto.class);


    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      return Objects.requireNonNull(responseEntity.getBody());
    } else {
      throw new InternalServerErrorException(
          "Unable to register user. Error code: " + responseEntity.getStatusCode());
    }
  }

  @Override
  public AccountDto play(String id, int myNum) {
    //get user
    AccountDto user = this.getUser(id);

    //get next number from API
    int nextNumber = this.getNextNumber();

    if (nextNumber == myNum) {
      //award user for the win
      user = this.awardUser(id);
      user.setResult(RESULT.WIN.toString());
    } else {
      //charge for playing the game
      user = this.chargeForSinglePlay(id);
      user.setResult(RESULT.LOSS.toString());
    }

    return user;
  }

  private AccountDto getUser(String id) {
    HttpHeaders headers = this.getHeaders();
    HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

    //adding the path params to the URL
    UriComponentsBuilder findUserUriBuilder = UriComponentsBuilder.fromHttpUrl(
        this.bankApiBaseUrl + "/user/" + id);

    ResponseEntity<AccountDto> responseEntity =
        restTemplate.exchange(
            findUserUriBuilder.toUriString(), HttpMethod.GET, requestEntity, AccountDto.class);

    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      return Objects.requireNonNull(responseEntity.getBody());
    } else {
      throw new InternalServerErrorException(
          "Unable to find user. Description: " + responseEntity.getBody());
    }
  }

  private AccountDto awardUser(String id) {
    HttpHeaders headers = this.getHeaders();
    HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

    //adding the path params to the URL
    UriComponentsBuilder creditUserUriBuilder = UriComponentsBuilder.fromHttpUrl(
        this.bankApiBaseUrl + "/user/"  + id + "/debit")
        .queryParam("amount", COST_PER_SINGLE_PLAY);

    ResponseEntity<AccountDto> responseEntity =
        restTemplate.exchange(
            creditUserUriBuilder.toUriString(), HttpMethod.PUT, requestEntity, AccountDto.class);

    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      return Objects.requireNonNull(responseEntity.getBody());
    } else {
      throw new InternalServerErrorException(
          "Unable to award user winning the game. Description: " + responseEntity.getBody());
    }
  }

  private AccountDto chargeForSinglePlay(String id) {
    HttpHeaders headers = this.getHeaders();
    HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

    //adding the path params to the URL
    UriComponentsBuilder creditUserUriBuilder = UriComponentsBuilder.fromHttpUrl(
        this.bankApiBaseUrl + "/user/"  + id + "/credit")
        .queryParam("amount", COST_PER_SINGLE_PLAY);

    ResponseEntity<AccountDto> responseEntity =
        restTemplate.exchange(
            creditUserUriBuilder.toUriString(), HttpMethod.PUT, requestEntity, AccountDto.class);

    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      return Objects.requireNonNull(responseEntity.getBody());
    } else {
      throw new InternalServerErrorException(
          "Unable to charge user account. Description: " + responseEntity.getBody());
    }
  }

  private int getNextNumber() {
    HttpHeaders headers = this.getHeaders();
    HttpEntity<HttpHeaders> requestEntity = new HttpEntity<>(headers);

    //adding the path params to the URL
    UriComponentsBuilder nextNumberUriBuilder = UriComponentsBuilder.fromHttpUrl(
        this.randomNumberApiBaseUrl + "/generate");

    ResponseEntity<Integer> responseEntity =
        restTemplate.exchange(
            nextNumberUriBuilder.toUriString(), HttpMethod.GET, requestEntity, Integer.class);

    if (responseEntity.getStatusCode().equals(HttpStatus.OK)) {
      return Objects.requireNonNull(responseEntity.getBody());
    } else {
      throw new InternalServerErrorException(
          "Unable to generate new numbers. Description: " + responseEntity.getBody());
    }
  }

  private HttpHeaders getHeaders() {
    //String accessToken = "";
    HttpHeaders headers = new HttpHeaders();
    //assert accessToken != null;
    //headers.setBearerAuth(accessToken);
    headers.setAccept(List.of(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON);
    return headers;
  }
}
