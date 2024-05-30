package com.wiinvent.gami.domain.service.user;

import com.wiinvent.gami.domain.entities.PremiumState;
import com.wiinvent.gami.domain.entities.SubState;
import com.wiinvent.gami.domain.entities.type.UserType;
import com.wiinvent.gami.domain.entities.user.*;
import com.wiinvent.gami.domain.exception.base.ResourceNotFoundException;
import com.wiinvent.gami.domain.pojo.UserSubStatusInfo;
import com.wiinvent.gami.domain.response.UserResponse;
import com.wiinvent.gami.domain.response.base.PageCursorResponse;
import com.wiinvent.gami.domain.response.type.CursorType;
import com.wiinvent.gami.domain.service.BaseService;
import com.wiinvent.gami.domain.utils.Constants;
import com.wiinvent.gami.domain.utils.DateUtils;
import com.wiinvent.gami.domain.utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserService extends BaseService {

  @Autowired
  UserSegmentService userSegmentService;


  /**
   * Cong point cho user
   */
  public void addPointForUser(User user, Long amount) {
    user.addPointForUser(amount);
  }

//  public PageCursorResponse<UserResponse> getAllUsers(UUID userId, String phoneNumber, Long next, Long pre, int limit) {
//    List<User> users = userStorage.findAll(userId, phoneNumber, next, pre, limit);
//    List<UserResponse> responses = modelMapper.toListUserResponse(users);
//    return new PageCursorResponse<>(responses, limit, next, pre, Constants.CREATED_AT_VARIABLE);
//  }

  public UserResponse getUserDetail(UUID userId) {
    User user = userStorage.findById(userId);
    if (user == null) {
      throw new ResourceNotFoundException("User id not found");
    }
    //segment
    UserSegment currentUserSegment = userSegmentStorage.findById(user.getUserSegmentId());
    //user account
    UserAccount userAccount = userAccountStorage.findById(userId);
    //response
    UserResponse userResponse = modelMapper.toUserResponse(user);
    UserProfile userProfile = userProfileStorage.findById(userResponse.getId());
    userResponse.setFirstName(userProfile.getFirstName());
    userResponse.setLastName(userProfile.getLastName());
    userResponse.setEmail(userProfile.getEmail());
    userResponse.setPhoneNumber(userProfile.getPhoneNumber());
    userResponse.setBirth(userProfile.getBirth());
    userResponse.setDisplayName(userProfile.getDisplayName());
    //user account
    if (Objects.nonNull(userAccount)) {
      userResponse.setState(userAccount.getState());
      userResponse.setStatus(userAccount.getStatus());
    }
    //segment
    if (Objects.nonNull(currentUserSegment)) {
      userResponse.setLevel(currentUserSegment.getLevel());
      userResponse.setLevelName(currentUserSegment.getName());
      userResponse.setPointBonusRate(currentUserSegment.getPointBonusRate());
      userResponse.setPointLimit(currentUserSegment.getPointLimit());
    }
    UserSubStatusInfo statusInfo = checkSubStatus(userResponse);
    int limitPoint = currentUserSegment.getPointLimit();
    if (Boolean.TRUE.equals(statusInfo.getIsPremium())) {
      limitPoint = currentUserSegment.getPointLimit() + currentUserSegment.getExtendPoint();
    }
    if (Boolean.TRUE.equals(statusInfo.getIsSub())) {
      limitPoint = limitPoint + (limitPoint * currentUserSegment.getSubBonusRate()) / 100;
    }
    userResponse.setPointUpLevel(Math.max(limitPoint - userResponse.getPoint(), 0));
    userResponse.setUserTypes(statusInfo.getUserTypes());
    UserSegment nextUserSegment = userSegmentStorage.findNextLevel(currentUserSegment.getLevel());
    if (Objects.nonNull(nextUserSegment)) {
      userResponse.setExpUpLevel(nextUserSegment.getRequireExp() - userResponse.getExp());
    }
    return userResponse;
  }

  public UserSubStatusInfo checkSubStatus(UserResponse userResponse) {
    Long nowAtUtc = DateUtils.getNowMillisAtUtc();
    PremiumState premiumState;
    SubState subState;
    premiumState = premiumStateStorage.findByPremiumStateAndUserIdAndEndAtGreaterThan(userResponse.getId(), nowAtUtc);
    subState = subStateStorage.findBySubStateAndUserIdAndEndAtGreaterThan(userResponse.getId(), nowAtUtc);
    return new UserSubStatusInfo(premiumState, subState);
  }

  public List<UUID> checkUserId(List<UUID> userIds, String displayName, String phoneNumber) {

    if (Objects.nonNull(displayName)) {
      List<UserProfile> userProfiles = userProfileStorage.findByDisplayName(displayName);
      if (Objects.nonNull(userProfiles) && !userProfiles.isEmpty()) {
        for (UserProfile userProfile : userProfiles) {
          userIds.add(userProfile.getId());
        }
      }
    }
    if (Objects.nonNull(phoneNumber)) {
      UserProfile userProfile = userProfileStorage.findByPhoneNumber(phoneNumber);
      if (Objects.nonNull(userProfile)) {
        userIds.add(userProfile.getId());
      }
    }
    return userIds;
  }

  public Long checkSegmentId(Integer level) {
    Long segmentId = null;
    if (Objects.nonNull(level)) {
      UserSegment userSegment = userSegmentStorage.findByLevel(level);
      if (Objects.nonNull(userSegment)) {
        segmentId = userSegment.getId();
      }
    }
    return segmentId;
  }

  public PageCursorResponse<UserResponse> getPageUser
      (UUID userId, String displayName, String phoneNumber, Integer level, UserType userType, Long next, Long pre, Integer limit, LocalDate startDate, LocalDate endDate) {
    Long startDateLong = null;
    Long endDateLong = null;
    List<UUID> userIds = new ArrayList<>();
    if (Objects.nonNull(userId)) {
      userIds.add(userId);
    }
    userIds = checkUserId(userIds, displayName, phoneNumber);
    if (Objects.nonNull(startDate)) startDateLong = Helper.startOfDaytoLong(startDate);
    if (Objects.nonNull(endDate)) endDateLong = Helper.endOfDaytoLong(endDate);
    Long segmentId = checkSegmentId(level);
    Long endSub = null;
    Long endPremium = null;
    if (Objects.equals(userType, UserType.SUB)) {
      endSub = DateUtils.getNowMillisAtUtc();
    } else if (Objects.equals(userType, UserType.PREMIUM)) {
      endPremium = DateUtils.getNowMillisAtUtc();
    }
    List<User> users = new ArrayList<>();
    CursorType type = CursorType.FIRST;
    if (next == null && pre == null) {
      next = Helper.getNowMillisAtUtc();
      pre = 0L;
      users = userStorage.findAllUser(userIds, segmentId, endSub, endPremium, next, pre, limit, startDateLong, endDateLong, type);
    } else if (pre == null) {
      type = CursorType.NEXT;
      pre = 0L;
      users = userStorage.findAllUser(userIds, segmentId, endSub, endPremium, next, pre, limit, startDateLong, endDateLong, type);
    } else if (next == null) {
      type = CursorType.PRE;
      next = Helper.getNowMillisAtUtc();
      users = userStorage.findAllUser(userIds, segmentId, endSub, endPremium, next, pre, limit, startDateLong, endDateLong, type);
      users = users.stream().sorted(Comparator.comparingLong(User::getCreatedAt).reversed()).toList();
    }
    List<UUID> userIdList = users.stream().map(User::getId).toList();
    //profile
    List<UserProfile> userProfiles = userProfileStorage.findAllByIdIn(userIdList);
    Map<UUID, UserProfile> userProfileMap = userProfiles.stream().collect(Collectors.toMap(UserProfile::getId, Function.identity()));

    List<UserSegment> userSegments = userSegmentStorage.findByIdIn(users.stream().map(User::getUserSegmentId).toList());
    Map<Long, UserSegment> userSegmentMap = userSegments.stream().collect(Collectors.toMap(UserSegment::getId, Function.identity()));
    //user account
    List<UserAccount> userAccounts = userAccountStorage.findUserAccountsByIdIn(userIdList);
    Map<UUID, UserAccount> uuidUserAccountMap = userAccounts.stream()
        .collect(Collectors.toMap(UserAccount::getId, Function.identity()));
    userAccounts.clear();

    //response
    List<UserResponse> userResponses = modelMapper.toListUserResponse(users);
    userResponses.forEach(r -> {
      //profile
      r.setFirstName(userProfileMap.get(r.getId()).getFirstName());
      r.setLastName(userProfileMap.get(r.getId()).getLastName());
      r.setEmail(userProfileMap.get(r.getId()).getEmail());
      r.setPhoneNumber(userProfileMap.get(r.getId()).getPhoneNumber());
      r.setBirth(userProfileMap.get(r.getId()).getBirth());
      r.setDisplayName(userProfileMap.get(r.getId()).getDisplayName());
      //user account
      r.setState(uuidUserAccountMap.get(r.getId()).getState());
      r.setStatus(uuidUserAccountMap.get(r.getId()).getStatus());
      r.setUserTypes(checkSubStatus(r).getUserTypes());

      r.setLevel(userSegmentMap.get(r.getUserSegmentId()).getLevel());
      r.setLevelName(userSegmentMap.get(r.getUserSegmentId()).getName());
    });
    return new PageCursorResponse<>(userResponses, limit, type, Constants.CREATED_AT_VARIABLE);
  }
}
