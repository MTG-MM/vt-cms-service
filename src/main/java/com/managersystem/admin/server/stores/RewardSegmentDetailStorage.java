package com.managersystem.admin.server.stores;

import com.managersystem.admin.server.entities.RewardSegmentDetail;
import com.managersystem.admin.server.stores.base.BaseStorage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewardSegmentDetailStorage extends BaseStorage {

  public List<RewardSegmentDetail> findByRewardSegmentId(Long rewardSegmentId) {
    return rewardSegmentDetailRepository.findByRewardSegmentId(rewardSegmentId);
  }

  public void save(RewardSegmentDetail rewardSegmentDetail) {
    rewardSegmentDetailRepository.save(rewardSegmentDetail);
  }
}
