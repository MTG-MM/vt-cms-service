package com.wiinvent.gami.domain.stores;

import com.wiinvent.gami.domain.entities.VoucherDetail;
import com.wiinvent.gami.domain.entities.type.RewardItemStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class VoucherDetailStorage extends BaseStorage {

  public List<VoucherDetail> findAll() {
    return voucherDetailRepository.findAll();
  }

  public void save(VoucherDetail voucherDetail) {
    voucherDetailRepository.save(voucherDetail);
  }

  public Page<VoucherDetail> findAll(Pageable pageable) {
    return voucherDetailRepository.findAll(pageable);
  }

  public VoucherDetail findById(UUID id) {
    return voucherDetailRepository.findById(id).orElse(null);
  }

  public void saveAll(List<VoucherDetail> voucherDetails) {
    voucherDetailRepository.saveAll(voucherDetails);
  }

  public List<VoucherDetail> getListVoucherDetailByStatus(int voucherStoreId, RewardItemStatus rewardItemStatus, int limit) {
    Pageable pageable = PageRequest.of(0, limit);
    return voucherDetailRepository.getListVoucherDetailByStatus(voucherStoreId, rewardItemStatus, pageable);
  }

  public void updateItemStatus(Long rewardSegmentId, Long rewardItemId, long limit) {
    voucherDetailRepository.updateItemStatus(rewardSegmentId, rewardItemId, limit);
  }

  public Page<VoucherDetail> findByStoreId(Long storeId, Pageable pageable) {
    return voucherDetailRepository.findByStoreId(storeId, pageable);
  }

  public Integer getListInPollVoucherInGivenInPool(Long rewardSegmentId, Long rewardItemId, long startDateAtVn, long endDateAtVn) {
    return voucherDetailRepository.getListInPollVoucherInGivenInPool(rewardSegmentId, rewardItemId, startDateAtVn, endDateAtVn);
  }
}
