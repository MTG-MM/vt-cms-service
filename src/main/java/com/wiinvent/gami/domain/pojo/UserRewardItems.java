package com.wiinvent.gami.domain.pojo;

import lombok.Data;

@Data
public class UserRewardItems {
  private Long id;
  private String rewardName;
  private Integer position = 0;
  private Integer rank = 0;
}