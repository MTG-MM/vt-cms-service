package com.wiinvent.gami.domain.stores;

import com.wiinvent.gami.domain.entities.type.PackageStateType;
import com.wiinvent.gami.domain.entities.PremiumState;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class PremiumStateStorage extends BaseStorage {
  public PremiumState findByPremiumStateAndUserIdAndEndAtGreaterThan(UUID userId, Long endAtNow){
    return premiumStateRepository.findByPremiumStateAndUserIdAndEndAtGreaterThan(PackageStateType.ACTIVE, userId, endAtNow);
  }

  public PremiumState findByPackageId(Integer id) {
    return premiumStateRepository.findPremiumStateByPackageId(id);
  }
}
