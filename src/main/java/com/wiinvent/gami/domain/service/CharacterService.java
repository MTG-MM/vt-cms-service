package com.wiinvent.gami.domain.service;

import com.wiinvent.gami.domain.dto.CharacterCreateDto;
import com.wiinvent.gami.domain.dto.CharacterUpdateDto;
import com.wiinvent.gami.domain.dto.PackageCreateDto;
import com.wiinvent.gami.domain.dto.PackageUpdateDto;
import com.wiinvent.gami.domain.entities.Character;
import com.wiinvent.gami.domain.entities.Package;
import com.wiinvent.gami.domain.entities.type.CharacterCategoryType;
import com.wiinvent.gami.domain.entities.type.Status;
import com.wiinvent.gami.domain.exception.BadRequestException;
import com.wiinvent.gami.domain.response.CharacterResponse;
import com.wiinvent.gami.domain.response.base.PageResponse;
import com.wiinvent.gami.domain.utils.Constant;
import com.wiinvent.gami.domain.utils.DateUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@Log4j2
public class CharacterService extends BaseService{
  @Autowired @Lazy
  CharacterService self;
  public Page<CharacterResponse> findAll(String name, CharacterCategoryType categoryType, Status status, Pageable pageable){
    Page<Character> characters = characterStorage.findAll(name, categoryType, status, pageable);
    return modelMapper.toPageCharacterResponse(characters);
  }

  public CharacterResponse getCharacterDetail(Integer id){
    Character character = characterStorage.findById(id);
    if (character == null) {
      throw new BadRequestException(Constant.CHARACTER_NOT_FOUND);
    }
    return modelMapper.toCharacterResponse(character);
  }
  public boolean createCharacter(CharacterCreateDto dto) {
    //validation
    if(Objects.isNull(dto.getStatus())) dto.setStatus(Status.ACTIVE);
    //map
    Character character = modelMapper.toCharacter(dto);
    //save
    try {
      self.save(character);
    } catch (Exception e){
      log.debug("==============>createCharacter:DB:Exception:{}", e.getMessage());
      return false;
    }
    return true;
  }

  public boolean updateCharacter(Integer id, CharacterUpdateDto dto) {
    //validation
    Character character = characterStorage.findById(id);
    if (character == null) {
      throw new BadRequestException(Constant.CHARACTER_NOT_FOUND);
    }
    modelMapper.mapCharacterUpdateDtoToCharacter(dto, character);
    //save
    try {
      self.save(character);
    } catch (Exception e){
      log.debug("==============>updateCharacter:DB:Exception:{}", e.getMessage());
      return false;
    }
    //cache
    try {
      remoteCache.deleteKey(cacheKey.getCharacterById(character.getId()));
    } catch (Exception e){
      log.debug("==============>updateCharacter:Cache:Exception:{}", e.getMessage());
    }
    return true;
  }

  public boolean deleteCharacter(Integer id) {
    Character character = characterStorage.findById(id);
    if (Objects.isNull(character)) {
      throw new BadRequestException(Constant.PACKAGE_NOT_FOUND);
    }
    character.setStatus(Status.DELETE);
    //save
    try {
      self.save(character);
    } catch (Exception e){
      log.debug("==============>deleteCharacter:DB:Exception:{}", e.getMessage());
      return false;
    }
    try {
      remoteCache.deleteKey(cacheKey.getCharacterById(character.getId()));
    } catch (Exception e){
      log.debug("==============>updateCharacter:Cache:Exception:{}", e.getMessage());
    }
    return true;
  }
  @Transactional(isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
  public void save(Character character){
    characterStorage.save(character);
  }
}