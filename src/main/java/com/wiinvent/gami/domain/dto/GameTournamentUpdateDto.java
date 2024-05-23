package com.wiinvent.gami.domain.dto;

import com.wiinvent.gami.domain.entities.type.Status;
import com.wiinvent.gami.domain.pojo.UserSegmentRewardItems;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class GameTournamentUpdateDto {
  private String name;
  private Integer gameId;
  private Long startAt;
  private Long endAt;
  private Long duration;
  private List<UserSegmentRewardItems> rewardItems;
  @NotNull
  private Status status;
}
