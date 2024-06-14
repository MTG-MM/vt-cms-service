package com.wiinvent.gami.domain.stores;

import com.wiinvent.gami.domain.repositories.AccountRepository;
import com.wiinvent.gami.domain.repositories.*;
import com.wiinvent.gami.domain.repositories.leaderboard.LeaderboardEventRepository;
import com.wiinvent.gami.domain.repositories.leaderboard.LeaderboardHistoryRepository;
import com.wiinvent.gami.domain.repositories.leaderboard.LeaderboardRepository;
import com.wiinvent.gami.domain.repositories.leaderboard.LeaderboardRewardRepository;
import com.wiinvent.gami.domain.repositories.statistic.StatisticCheckpointRepository;
import com.wiinvent.gami.domain.repositories.statistic.StatisticRevenueRepository;
import com.wiinvent.gami.domain.repositories.statistic.StatisticSubRepository;
import com.wiinvent.gami.domain.repositories.statistic.StatisticUserRepository;
import com.wiinvent.gami.domain.repositories.transaction.*;
import com.wiinvent.gami.domain.repositories.game.*;
import com.wiinvent.gami.domain.repositories.gvc.GcvHistoryRepository;
import com.wiinvent.gami.domain.repositories.gvc.GvcPackageRepository;
import com.wiinvent.gami.domain.repositories.payment.PaymentMethodRepository;
import com.wiinvent.gami.domain.repositories.payment.PackageHistoryRepository;
import com.wiinvent.gami.domain.repositories.reward.*;
import com.wiinvent.gami.domain.repositories.user.*;
import com.wiinvent.gami.domain.utils.CacheKey;
import com.wiinvent.gami.domain.utils.RemoteCache;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class BaseStorage {

  @Autowired protected EntityManager entityManager;
  @Autowired protected RemoteCache remoteCache;
  @Autowired protected CacheKey cacheKey;
  @Autowired protected AccountRepository accountRepository;
  @Autowired protected ConfigRepository configRepository;
  @Autowired protected UserRepository userRepository;
  @Autowired protected RewardItemHistoryRepository rewardItemHistoryRepository;
  @Autowired protected RewardItemRepository rewardItemRepository;
  @Autowired protected RewardScheduleRepository rewardScheduleRepository;
  @Autowired protected RewardSegmentDetailRepository rewardSegmentDetailRepository;
  @Autowired protected RewardSegmentRepository rewardSegmentRepository;
  @Autowired protected RewardStateRepository rewardStateRepository;
  @Autowired protected RewardStateLogRepository rewardStateLogRepository;
  @Autowired protected RewardTypeRepository rewardTypeRepository;
  @Autowired protected RewardItemStoreRepository rewardItemStoreRepository;
  @Autowired protected VoucherDetailRepository voucherDetailRepository;
  @Autowired protected ProductDetailRepository productDetailRepository;
  @Autowired protected UserSegmentRepository userSegmentRepository;
  @Autowired protected RewardItemStatisticRepository rewardItemStatisticRepository;
  @Autowired protected GameCategoryRepository gameCategoryRepository;
  @Autowired protected GamePackageRepository gamePackageRepository;
  @Autowired protected GameRepository gameRepository;
  @Autowired protected GvcPackageRepository gvcPackageRepository;
  @Autowired protected GcvHistoryRepository gcvHistoryRepository;
  @Autowired protected PackageRepository packageRepository;
  @Autowired protected PaymentMethodRepository paymentMethodRepository;
  @Autowired protected GameLikeRepository gameLikeRepository;
  @Autowired protected GameStarRepository gameStarRepository;
  @Autowired protected GamePaymentTransactionRepository gamePaymentTransactionRepository;
  @Autowired protected PackageHistoryRepository packageHistoryRepository;
  @Autowired protected FriendRepository friendRepository;
  @Autowired protected UserNotifyRepository userNotifyRepository;
  @Autowired protected GameTypeRepository gameTypeRepository;
  @Autowired protected UserProfileRepository userProfileRepository;
  @Autowired protected CoinTransactionRepository coinTransactionRepository;
  @Autowired protected PointTransactionRepository pointTransactionRepository;
  @Autowired protected ExpHistoryRepository expHistoryRepository;
  @Autowired protected TurnTransactionRepository turnTransactionRepository;
  @Autowired protected GameTournamentRepository gameTournamentRepository;
  @Autowired protected CharacterRepository characterRepository;
  @Autowired protected GameTournamentUserRepository gameTournamentUserRepository;
  @Autowired protected GameTournamentEventRepository gameTournamentEventRepository;
  @Autowired protected UserAccountRepository userAccountRepository;
  @Autowired protected FeatureRepository featureRepository;
  @Autowired protected PackageTypeRepository packageTypeRepository;
  @Autowired protected BannerRepository bannerRepository;
  @Autowired protected CharacterUserTransactionRepository characterUserTransactionRepository;
  @Autowired protected AchievementRepository achievementRepository;
  @Autowired protected SubStateRepository subStateRepository;
  @Autowired protected PremiumStateRepository premiumStateRepository;
  @Autowired protected ChallengeRepository challengeRepository;
  @Autowired protected ChallengeDetailRepository challengeDetailRepository;
  @Autowired protected CollectionRepository collectionRepository;
  @Autowired protected CollectionTransactionRepository collectionTransactionRepository;
  @Autowired protected TicketHistoryRepository ticketHistoryRepository;
  @Autowired protected UserCollectionRepository userCollectionRepository;
  @Autowired protected AchievementUserRepository achievementUserRepository;
  @Autowired protected ExchangeItemStoreRepository exchangeItemStoreRepository;
  @Autowired protected QuestRepository questRepository;
  @Autowired protected QuestTurnRepository questTurnRepository;
  @Autowired protected TaskRepository taskRepository;
  @Autowired protected QuestUserHistoryRepository questUserHistoryRepository;
  @Autowired protected StatisticRevenueRepository statisticRevenueRepository;
  @Autowired protected StatisticUserRepository statisticUserRepository;
  @Autowired protected StatisticSubRepository statisticSubRepository;
  @Autowired protected StatisticCheckpointRepository statisticCheckpointRepository;
  @Autowired protected UserGoldPigRepository userGoldPigRepository;
  @Autowired protected TaskUserRepository taskUserRepository;
  @Autowired protected CharacterUserRepository characterUserRepository;
  @Autowired protected LeaderboardRepository leaderboardRepository;
  @Autowired protected LeaderboardEventRepository leaderboardEventRepository;
  @Autowired protected LeaderboardRewardRepository leaderboardRewardRepository;
  @Autowired protected LeaderboardHistoryRepository leaderboardHistoryRepository;
}
