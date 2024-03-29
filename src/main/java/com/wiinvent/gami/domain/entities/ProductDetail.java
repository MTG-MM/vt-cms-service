package com.wiinvent.gami.domain.entities;

import com.wiinvent.gami.domain.entities.type.RewardItemStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "product_detail")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductDetail extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "id", columnDefinition = "BINARY(16)")
  private UUID id;

  @Column(name = "store_id")
  private Long storeId;

  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  @Column(name = "user_id", columnDefinition = "BINARY(16)")
  private UUID userId;

  @Column(name = "given_at")
  private Long givenAt;

  @Column(name = "product_status")
  @Enumerated(EnumType.STRING)
  private RewardItemStatus status;

  @Column(name = "given_to_pool")
  private Long givenToPool;

  @Column(name = "reward_segment_id")
  private Long rewardSegmentId;

  @Column(name = "reward_item_id")
  private Long rewardItemId;

  @Column(name = "start_at")
  private Long startAt;

  @Column(name = "expire_at")
  private Long expireAt;
}
