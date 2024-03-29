package com.wiinvent.gami.domain.dto;

import lombok.Data;

@Data
public class GameUpdateDto{

  private Integer categoryId;

  private String name;

  private String imageUrl;

  private String secretKey;

  private String thumbUrl;

  private String banner;

  private String description;

  private Integer priority;

  private String navLink;

  private String apiVerifyAccount;

  private String bodyApiVerifyAccount;

  private String apiPayment;

  private String bodyApiPayment;

}
