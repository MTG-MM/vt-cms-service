package com.managersystem.admin.server.service;

import com.managersystem.admin.handleRequest.controller.dto.AccountDto;
import com.managersystem.admin.handleRequest.controller.dto.LoginDto;
import com.managersystem.admin.handleRequest.controller.dto.UserInfoDto;
import com.managersystem.admin.handleRequest.controller.response.TokenResponse;
import com.managersystem.admin.server.entities.Account;
import com.managersystem.admin.server.entities.type.NewAccountState;
import com.managersystem.admin.server.entities.type.Rank;
import com.managersystem.admin.server.entities.type.State;
import com.managersystem.admin.server.entities.type.UserRole;
import com.managersystem.admin.server.exception.BadRequestException;
import com.managersystem.admin.server.exception.base.ErrorCode;
import com.managersystem.admin.server.security.JwtService;
import com.managersystem.admin.server.security.UserSecurityService;
import com.managersystem.admin.server.service.base.BaseService;
import com.managersystem.admin.server.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class AccountService extends BaseService {

  @Autowired
  JwtService jwtService;

  @Autowired UserService userService;

  @Autowired
  @Lazy
  UserSecurityService userSecurityService;

  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public Account createAccount(AccountDto dto){
    Account account = new Account();
    account.setId(UUID.randomUUID());
    account.setUsername(dto.getUsername());
    account.setPassword(userSecurityService.encode(dto.getPassword()));
    account.setRank(Rank.BRONZE);
    account.setState(State.NOT_VERIFY);
    account.setAccountState(NewAccountState.CREATE_ACCOUNT);
    account.setRole(UserRole.OPERATOR);
    accountStorage.save(account);
    userService.createUserInfo(account.getId(), dto.getUserInfoDto());
    return account;
  }

  public TokenResponse login(LoginDto dto){
    Account account = accountStorage.findByUsername(dto.getUsername());
    if(account == null){
      throw new BadRequestException("Invalid username or password", ErrorCode.INVALID_USERNAME_OR_PASSWORD);
    }
    if(!userSecurityService.decode(dto.getPassword(), account.getPassword())){
      throw new BadRequestException("Invalid username or password", ErrorCode.INVALID_USERNAME_OR_PASSWORD);
    }
    String token = jwtService.generateToken(account);
    return new TokenResponse(token, account.getRole(), account.getAccountState(), account.getState());
  }

  public boolean initAdminAccount() {
    Account account = new Account();
    account.setId(UUID.randomUUID());
    account.setUsername("MosSystemAdmin");
    account.setPassword(userSecurityService.encode("admin123456"));
    account.setRole(UserRole.ADMIN);
    account.setRank(Rank.DIAMOND);
    account.setState(State.VERIFY);
    account.setAccountState(NewAccountState.COMPLETE);
    account.setRole(UserRole.ADMIN);
    accountStorage.save(account);
    return true;
  }

  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public List<TokenResponse> initAccountTest() {
    List<TokenResponse> responses = new ArrayList<>();
    List<Account> accounts = new ArrayList<>();
    for(int i = 0 ; i < 100; i++){
      Account account = accountStorage.findByUsername("acc" + i);
      if(account == null){
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername("acc" + i);
        accountDto.setPassword("acc" + i);
        UserInfoDto userInfoDto = new UserInfoDto();
        userInfoDto.setLastName("acc first name " + i);
        userInfoDto.setLastName("acc last name " + i);
        userInfoDto.setPhoneNumber("acc phone " + i);
        userInfoDto.setBirth("acc " + i);
        userInfoDto.setEmail("acc last name " + i);
        userInfoDto.setCurrentAddress("acc last name " + i);
        accountDto.setUserInfoDto(userInfoDto);
        account = createAccount(accountDto);
        accounts.add(account);
      }
    }
    accounts.forEach(acc -> {
      responses.add(new TokenResponse(jwtService.generateToken(acc), acc.getRole(), acc.getAccountState(), acc.getState() ));
    });
    return responses;
  }
}
