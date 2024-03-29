package com.wiinvent.gami.domain.service;

import com.wiinvent.gami.ModelMapper;
import com.wiinvent.gami.domain.stores.*;
import com.wiinvent.gami.domain.stores.game.*;
import com.wiinvent.gami.domain.stores.payment.PaymentMethodStorage;
import com.wiinvent.gami.domain.stores.payment.PaymentTransactionStorage;
import com.wiinvent.gami.domain.stores.reward.*;
import com.wiinvent.gami.domain.stores.user.UserNotifyStorage;
import com.wiinvent.gami.domain.stores.user.UserSegmentStorage;
import com.wiinvent.gami.domain.stores.user.UserStorage;
import com.wiinvent.gami.domain.utils.CacheKey;
import com.wiinvent.gami.domain.utils.LockManager;
import com.wiinvent.gami.domain.utils.RemoteCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseService {
  @Autowired protected ModelMapper modelMapper;
  @Autowired protected RemoteCache remoteCache;
  @Autowired protected CacheKey cacheKey;
  @Autowired protected LockManager lockManager;
  @Autowired protected AccountStorage accountStorage;
  @Autowired protected ConfigStorage configStorage;
  @Autowired protected UserStorage userStorage;
  @Autowired protected UserSegmentStorage userSegmentStorage;
  @Autowired protected RewardItemHistoryStorage rewardItemHistoryStorage;
  @Autowired protected RewardItemStorage rewardItemStorage;
  @Autowired protected RewardScheduleStorage rewardScheduleStorage;
  @Autowired protected RewardSegmentDetailStorage rewardSegmentDetailStorage;
  @Autowired protected RewardSegmentStorage rewardSegmentStorage;
  @Autowired protected RewardStateStorage rewardStateStorage;
  @Autowired protected RewardStateLogStorage rewardStateLogStorage;
  @Autowired protected RewardTypeStorage rewardTypeStorage;
  @Autowired protected VoucherDetailStorage voucherDetailStorage;
  @Autowired protected ProductDetailStorage productDetailStorage;
  @Autowired protected RewardItemStoreStorage rewardItemStoreStorage;
  @Autowired protected RewardItemStatisticStorage rewardItemStatisticStorage;
  @Autowired protected GameLikeStorage gameLikeStorage;
  @Autowired protected GameStarStorage gameStarStorage;
  @Autowired protected GameStorage gameStorage;
  @Autowired protected GamePackageStorage gamePackageStorage;
  @Autowired protected PaymentMethodStorage paymentMethodStorage;
  @Autowired protected PaymentTransactionStorage paymentTransactionStorage;
  @Autowired protected GamePaymentTransactionStorage gamePaymentTransactionStorage;
  @Autowired protected FriendStorage friendStorage;
  @Autowired protected UserNotifyStorage userNotifyStorage;

}
