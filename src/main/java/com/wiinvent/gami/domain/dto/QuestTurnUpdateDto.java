package com.wiinvent.gami.domain.dto;

import com.wiinvent.gami.domain.entities.type.Status;
import com.wiinvent.gami.domain.entities.type.UserType;
import com.wiinvent.gami.domain.pojo.UserRewardItems;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class QuestTurnUpdateDto {
  private String name;
  private Integer questNumber;

  @NotNull
  private Integer turn;
  @NotNull
  private Integer pointPrice;
  @NotNull
  private Integer coinPrice;

  private Integer questCd;

  private Integer viewAdsSkipDuration;

  private Integer coinSkipCdPrice;

  @NotNull
  private Integer duration;

  private Integer durationBonus;

  private Integer durationBonusPrice;
  @NotNull
  private Status status;

  private List<UserType> activeForUser = new ArrayList<>();

  private List<UserType> freeForUser = new ArrayList<>();

  private List<UserRewardItems> rewardItems = new ArrayList<>();
}
