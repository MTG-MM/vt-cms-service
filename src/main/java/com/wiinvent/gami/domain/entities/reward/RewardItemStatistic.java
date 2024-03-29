package com.wiinvent.gami.domain.entities.reward;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reward_item_statistic")
public class RewardItemStatistic {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "date")
  private LocalDate date;

  @Column(name = "reward_segment_id")
  private Long rewardSegmentId;

  @Column(name = "reward_item_id")
  private Long rewardItemId;

  @Column(name = "total_reward_received")
  private Integer totalRewardReceived;

  @Column(name = "total_user")
  private Integer totalUser;

  @Column(name = "total_reward_remain") // So qua con lại trong kho
  private Integer totalRewardRemain; // Khong duoc cap nhat khi chạy lại thống kê




}
