package com.wiinvent.gami.domain.response;

import lombok.Data;

import java.util.UUID;

@Data
public class GameResponse {
  private Integer id;
  private String name;
  private Integer categoryId;
  private UUID teamId;
  private String secretKey;
  private Integer priority;
  private String imageUrl;
  private String thumbUrl;
  private String banner;
  private String navLink;
  private String apiVerifyAccount;
  private String bodyApiVerifyAccount;
  private String apiPayment;
  private String bodyApiPayment;
  private Integer gameTypeId;
  private String description;
  private Long createdAt;
  private Long updatedAt;
}
