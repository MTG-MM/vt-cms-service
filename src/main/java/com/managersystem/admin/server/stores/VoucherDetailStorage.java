package com.managersystem.admin.server.stores;

import com.managersystem.admin.server.entities.VoucherDetail;
import com.managersystem.admin.server.stores.base.BaseStorage;
import org.springframework.data.domain.Page;
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
}
