package com.wiinvent.gami.domain.entities.reward;


import com.wiinvent.gami.domain.entities.BaseEntity;
import com.wiinvent.gami.domain.entities.type.RewardItemType;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "reward_item_history")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class RewardItemHistory extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(name = "reward_name")
  private String name;

  @Column(name = "reward_type")
  @Enumerated(EnumType.STRING)
  private RewardItemType type;

  @Column(name = "user_id", columnDefinition = "BINARY(16)")
  private UUID userId;

  @Column(name = "reward_item_id")
  private Long rewardItemId; // id reward item

  @Column(name = "reward_item_detail_id", columnDefinition = "BINARY(16)")
  private UUID rewardItemDetailId; // id qua trung thuong, (voucher <=> voucherDetailId, product <=> productDetailId)

  @Column(name = "reward_segment_id")
  private Long rewardSegmentId;

  @Column(name = "image_url")
  private String imageUrl;

  @Column(name = "note")
  private String note;

  @Column(name = "reward_info")
  private String rewardInfo;
}
