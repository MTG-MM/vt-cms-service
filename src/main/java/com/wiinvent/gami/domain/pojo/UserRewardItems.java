package com.wiinvent.gami.domain.pojo;

import lombok.Data;

@Data
public class UserRewardItems {
  private Long rewardItemId;
  private Integer position = 0;
  private Integer rank = 0;
}