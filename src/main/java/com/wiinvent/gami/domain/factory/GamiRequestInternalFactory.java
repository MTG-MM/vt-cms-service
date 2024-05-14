package com.wiinvent.gami.domain.factory;

import com.wiinvent.gami.domain.dto.InternalRequestDto;
import com.wiinvent.gami.domain.dto.InternalResetPassDto;
import com.wiinvent.gami.domain.dto.InternalSubRequestDto;
import com.wiinvent.gami.domain.exception.RequestFailedException;
import com.wiinvent.gami.domain.response.InternalRequestResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Log4j2
public class GamiRequestInternalFactory {
  @Value("${GAMI_SERVICE_DOMAIN}")
  private String gamiServiceDomain;

  @Autowired
  private RestTemplate httpRestTemplate;

  public InternalRequestResponse addSub(InternalSubRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<InternalSubRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("=========addPackage: " + gamiServiceDomain + "/v1/game/it/portal/package/add");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/package/add",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========addPackage: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse addCoin(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("=========addCoin: " + gamiServiceDomain + "/v1/game/it/portal/coin/add");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/coin/add",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========addCoin: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse subCoin(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("=========subCoin: " + gamiServiceDomain + "v1/game/it/portal/coin/subtract");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/coin/subtract",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========subCoin: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse addPoint(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("=========addPoint: " + gamiServiceDomain + "/v1/game/it/portal/point/add");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/point/add",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========addPoint: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse subPoint(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);

      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("=========subPoint: " + gamiServiceDomain + "/v1/game/it/portal/point/subtract");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/point/subtract",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========subPoint: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse addExp(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========addExp: " + gamiServiceDomain + "/v1/game/it/portal/exp/add");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/exp/add",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========addExp: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse subExp(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========subExp: " + gamiServiceDomain + "/v1/game/it/portal/exp/subtract");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/exp/subtract",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========subExp: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse addTurn(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========addTurn: " + gamiServiceDomain + "/v1/game/it/portal/turn/add");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/turn/add",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========addTurn: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse subTurn(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========subTurn: " + gamiServiceDomain + "/v1/game/it/portal/turn/subtract");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/turn/subtract",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========subTurn: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse addTicket(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========addTicket: " + gamiServiceDomain + "/v1/game/it/portal/ticket/add");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/ticket/add",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========addTicket: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse subTicket(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========subTicket: " + gamiServiceDomain + "/v1/game/it/portal/ticket/subtract");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/ticket/subtract",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========subTicket: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse addCollection(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========addCollection: " + gamiServiceDomain + "/v1/game/it/portal/collection/add");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/collection/add",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========addCollection: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse subCollection(InternalRequestDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalRequestDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========subCollection: " + gamiServiceDomain + "/v1/game/it/portal/collection/subtract");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/collection/subtract",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class);
      log.debug("=========subCollection: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }

  public InternalRequestResponse resetPassword(InternalResetPassDto dto) {
    try {
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<InternalResetPassDto> requestEntity = new HttpEntity<>(dto, headers);
      log.debug("==========resetPass: " + gamiServiceDomain + "/v1/game/it/portal/user/reset-password");
      ResponseEntity<InternalRequestResponse> response = httpRestTemplate.exchange(
          gamiServiceDomain + "/v1/game/it/portal/user/reset-password",
          HttpMethod.PUT,
          requestEntity,
          InternalRequestResponse.class
      );
      log.debug("=========resetPass: " + gamiServiceDomain + response.getBody());
      if (response.getBody() == null || !response.getBody().getSuccess()) {
        throw new RequestFailedException(response.getBody().getMessage());
      }
      return response.getBody();
    } catch (Exception e) {
      throw new RequestFailedException(e.getMessage());
    }
  }
}
